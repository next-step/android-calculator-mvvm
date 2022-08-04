package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding
import edu.nextstep.camp.calculator.domain.Expression
import kotlinx.coroutines.launch

class CalculatorActivity : AppCompatActivity(), CalculatorContract.View {
    private lateinit var binding: ActivityCalculatorBinding
    private val calculatorVM by viewModels<CalculatorViewModel>()
    override lateinit var presenter: CalculatorContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater).apply {
            this.lifecycleOwner = this@CalculatorActivity
            viewModel = calculatorVM
        }
        setContentView(binding.root)
        presenter = CalculatorPresenter(this)

        observeIncompleteExpressionErrorEvent()
    }

    private fun observeIncompleteExpressionErrorEvent() = lifecycleScope.launch {
        calculatorVM.incompleteExpressionErrorEvent.collect {
            showIncompleteExpressionError()
        }
    }

    override fun showExpression(expression: Expression) {
        binding.textView.text = expression.toString()
    }

    override fun showResult(result: Int) {
        binding.textView.text = result.toString()
    }

    override fun showIncompleteExpressionError() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }
}
