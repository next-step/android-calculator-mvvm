package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding
import edu.nextstep.camp.domain.Expression
import edu.nextstep.camp.domain.Operator

class CalculatorActivity : AppCompatActivity(), CalculatorContract.View {

    private val viewModel: CalculatorViewModel by viewModels()

    private lateinit var binding: ActivityCalculatorBinding
    override lateinit var presenter: CalculatorContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        presenter = CalculatorPresenter(this)

        initViews()
        observeLiveData()
    }

    private fun observeLiveData() {
//        viewModel.expressionEvent.observe(this) {
//            binding.textView.text = it.toString()
//        }

        viewModel.errorEvent.observe(this) {
            Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
        }
    }

    override fun showExpression(expression: Expression) {
//        binding.textView.text = expression.toString()
    }

    override fun showResult(result: Int) {
//        binding.textView.text = result.toString()
    }

    override fun showIncompleteExpressionError() {
//        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }

    private fun initViews() {
        binding.button0.setOnClickListener { viewModel.addToExpression(0) }
        binding.button1.setOnClickListener { viewModel.addToExpression(1) }
        binding.button2.setOnClickListener { viewModel.addToExpression(2) }
        binding.button3.setOnClickListener { viewModel.addToExpression(3) }
        binding.button4.setOnClickListener { viewModel.addToExpression(4) }
        binding.button5.setOnClickListener { viewModel.addToExpression(5) }
        binding.button6.setOnClickListener { viewModel.addToExpression(6) }
        binding.button7.setOnClickListener { viewModel.addToExpression(7) }
        binding.button8.setOnClickListener { viewModel.addToExpression(8) }
        binding.button9.setOnClickListener { viewModel.addToExpression(9) }
        binding.buttonPlus.setOnClickListener { viewModel.addToExpression(Operator.Plus) }
        binding.buttonMinus.setOnClickListener { viewModel.addToExpression(Operator.Minus) }
        binding.buttonMultiply.setOnClickListener { viewModel.addToExpression(Operator.Multiply) }
        binding.buttonDivide.setOnClickListener { viewModel.addToExpression(Operator.Divide) }
        binding.buttonDelete.setOnClickListener { viewModel.removeLast() }
        binding.buttonEquals.setOnClickListener { viewModel.calculate() }
    }
}
