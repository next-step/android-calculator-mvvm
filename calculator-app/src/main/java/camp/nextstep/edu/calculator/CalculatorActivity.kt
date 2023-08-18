package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityCalculatorBinding
import camp.nextstep.edu.calculator.domain.ArithmeticOperator

class CalculatorActivity : AppCompatActivity(), CalculatorContract.View {
    private lateinit var binding: ActivityCalculatorBinding
    override lateinit var presenter: CalculatorContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = CalculatorPresenter(this)
        initOperandButtons()
        initOperatorButtons()

        with(binding) {
            buttonEquals.setOnClickListener { presenter.onEqualsClicked() }
            buttonDelete.setOnClickListener { presenter.onDeleteClicked() }
        }
    }

    override fun showExpression(expression: String) {
        binding.textView.text = expression
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun initOperandButtons() {
        with(binding) {
            button0.setOnClickListener { presenter.onOperandClicked("0") }
            button1.setOnClickListener { presenter.onOperandClicked("1") }
            button2.setOnClickListener { presenter.onOperandClicked("2") }
            button3.setOnClickListener { presenter.onOperandClicked("3") }
            button4.setOnClickListener { presenter.onOperandClicked("4") }
            button5.setOnClickListener { presenter.onOperandClicked("5") }
            button6.setOnClickListener { presenter.onOperandClicked("6") }
            button7.setOnClickListener { presenter.onOperandClicked("7") }
            button8.setOnClickListener { presenter.onOperandClicked("8") }
            button9.setOnClickListener { presenter.onOperandClicked("9") }
        }
    }

    private fun initOperatorButtons() {
        with(binding) {
            buttonPlus.setOnClickListener { presenter.onOperatorClicked(ArithmeticOperator.PLUS) }
            buttonMinus.setOnClickListener { presenter.onOperatorClicked(ArithmeticOperator.MINUS) }
            buttonMultiply.setOnClickListener { presenter.onOperatorClicked(ArithmeticOperator.MULTIPLY) }
            buttonDivide.setOnClickListener { presenter.onOperatorClicked(ArithmeticOperator.DIVIDE) }
        }
    }
}
