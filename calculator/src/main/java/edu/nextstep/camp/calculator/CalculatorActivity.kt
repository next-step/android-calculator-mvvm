package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val viewModel: CalculatorViewModel by viewModels { CalculatorViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val resultAdapter = viewModel.getResultAdapter()
        binding.recyclerView.adapter = resultAdapter

        viewModel.showErrorMessage.observe(this) {
            if (it.consumed) return@observe
            Toast.makeText(this, "완성되지 않은 수식입니다", Toast.LENGTH_LONG).show()
            it.consume()
        }
    }
}