package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.calculationMemoryAdapter.CalculationMemoryAdapter
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val viewModel: CalculatorViewModel by viewModels { ViewModelFactory(this) }
    private lateinit var calculationMemoryAdapter: CalculationMemoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setRecyclerView()
        observeViewModel()
    }

    private fun setRecyclerView() {
        calculationMemoryAdapter = CalculationMemoryAdapter()
        binding.recyclerView.adapter = calculationMemoryAdapter
    }

    private fun observeViewModel() {
        viewModel.errorEvent.observe(this) {
            when (it.consume()) {
                CalculatorViewModel.ErrorEvent.INCOMPLETE_EXPRESSION_ERROR -> showIncompleteExpressionError()
                else -> {}
            }
        }
        viewModel.updateMemory.observe(this) {
            calculationMemoryAdapter.submitList(it)
        }
    }

    private fun setupViewModel() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.loadCalculationMemory()
    }

    private fun showIncompleteExpressionError() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }
}
