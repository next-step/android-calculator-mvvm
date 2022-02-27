package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.dodobest.data.*
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private lateinit var calculatorRepository: CalculatorRepositoryImpl
    private lateinit var resultAdapter: ResultAdapter
    private val viewModel: CalculatorViewModel by viewModels { CalculatorViewModelFactory(this, calculatorRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        calculatorRepository = CalculatorRepositoryImpl(AppDatabase.getInstance(this))
        resultAdapter = ResultAdapter(calculatorRepository.getMemories())

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = resultAdapter

        viewModel.showErrorMessage.observe(this) {
            if (it.consumed) return@observe
            Toast.makeText(this, "완성되지 않은 수식입니다", Toast.LENGTH_LONG).show()
            it.consume()
        }

        viewModel.calculationMemories.observe(this) {
            resultAdapter.setResult(it)
        }
    }
}