package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.errorEvent.observe(this) {
            when (it.consume()) {
                CalculatorViewModel.ErrorEvent.INCOMPLETE_EXPRESSION_ERROR -> showIncompleteExpressionError()
                else -> {}
            }
        }
    }

    private fun setupViewModel() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun showIncompleteExpressionError() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }
}
