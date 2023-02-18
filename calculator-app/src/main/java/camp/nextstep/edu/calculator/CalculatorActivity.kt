package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import camp.nextstep.edu.calculator.databinding.ActivityCalculatorBinding
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator


class CalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorBinding

    private val viewModel: CalculatorViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()

        setBindingVariables()

//        binding.button0.setOnClickListener { presenter.addToExpression(0) }
//        binding.button1.setOnClickListener { presenter.addToExpression(1) }
//        binding.button2.setOnClickListener { presenter.addToExpression(2) }
//        binding.button3.setOnClickListener { presenter.addToExpression(3) }
//        binding.button4.setOnClickListener { presenter.addToExpression(4) }
//        binding.button5.setOnClickListener { presenter.addToExpression(5) }
//        binding.button6.setOnClickListener { presenter.addToExpression(6) }
//        binding.button7.setOnClickListener { presenter.addToExpression(7) }
//        binding.button8.setOnClickListener { presenter.addToExpression(8) }
//        binding.button9.setOnClickListener { presenter.addToExpression(9) }
//        binding.buttonPlus.setOnClickListener { presenter.addToExpression(Operator.Plus) }
//        binding.buttonMinus.setOnClickListener { presenter.addToExpression(Operator.Minus) }
//        binding.buttonMultiply.setOnClickListener { presenter.addToExpression(Operator.Multiply) }
//        binding.buttonDivide.setOnClickListener { presenter.addToExpression(Operator.Divide) }
//        binding.buttonDelete.setOnClickListener { presenter.removeLast() }
//        binding.buttonEquals.setOnClickListener { presenter.calculate() }
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView<ActivityCalculatorBinding>(
            this, R.layout.activity_calculator
        ).apply {
            lifecycleOwner = this@CalculatorActivity
        }
    }

    private fun setBindingVariables() {
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
    }

//    override fun showExpression(expression: Expression) {
//        binding.textView.text = expression.toString()
//    }
//
//    override fun showResult(result: Int) {
//        binding.textView.text = result.toString()
//    }
//
//    override fun showIncompleteExpressionError() {
//        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
//    }
}
