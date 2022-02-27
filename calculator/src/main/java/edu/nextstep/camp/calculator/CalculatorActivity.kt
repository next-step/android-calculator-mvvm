package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.adapter.CalculationMemoryAdapter
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val viewModel: CalculatorViewModel by viewModels()
    private val calculationMemoryAdapter by lazy { CalculationMemoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initRecyclerView()
        showMessage()
        getCalculationMemory()
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = calculationMemoryAdapter
    }

    private fun showMessage() {
        viewModel.showExpressionErrorMessage.observe(this) {
            if (it.consumed) return@observe
            Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
            it.consume()
        }
    }

    private fun getCalculationMemory() {
        viewModel.calculationMemories.observe(this) {
            calculationMemoryAdapter.submitList(it)
        }
    }
}
