package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorActivity : AppCompatActivity(), CalculatorContract.View {
    private lateinit var binding: ActivityCalculatorBinding
    private val viewModel: CalculatorViewModel by viewModels { CalculatorViewModelFactory(this) }

    override lateinit var presenter: CalculatorContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = CalculatorPresenter(this)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.showErrorMessage.observe(this) {
            if (it.consumed) return@observe
            Toast.makeText(this, "완성되지 않은 수식입니다", Toast.LENGTH_LONG).show()
            it.consume()
        }

        setObserve()
    }

    private fun setObserve() {
        binding.buttonEquals.setOnClickListener { presenter.calculate() }
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