package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding
import edu.nextstep.camp.domain.calculator.Expression

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        observeViewState()
    }

    private fun observeViewState() {
        viewModel.onViewState.observe(this) {
            it.consume()?.run {
                when (this) {
                    is CalculatorState.ShowExpression -> showExpression(expression)
                    is CalculatorState.ShowIncompleteExpressionError -> showIncompleteExpressionError()
                    is CalculatorState.ShowResult -> showResult(result)
                }
            }
        }
    }

    private fun showExpression(expression: Expression) {
        binding.textView.text = expression.toString()
    }

    private fun showResult(result: Int) {
        binding.textView.text = result.toString()
    }

    private fun showIncompleteExpressionError() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }
}
