package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val viewModel: CalculatorViewModel by viewModels { ViewModelFactory(this) }
    private val calculatorHistoryAdapter = CalculatorHistoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initRecyclerView()
        viewModel.getCalculateHistories()

        viewModel.showIncompleteExpressionError.observe(this) {
            showIncompleteExpressionError()
        }
        viewModel.calculateHistory.observe(this) { calculateHistory ->
            calculatorHistoryAdapter.submitList(calculateHistory.calculateResults)
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            adapter = calculatorHistoryAdapter
        }
    }

    private fun showIncompleteExpressionError() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }
}
