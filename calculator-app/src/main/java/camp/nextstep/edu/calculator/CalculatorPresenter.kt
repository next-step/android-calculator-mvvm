package camp.nextstep.edu.calculator

import camp.nextstep.edu.calculator.domain.ArithmeticExpression
import camp.nextstep.edu.calculator.domain.ArithmeticOperator
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression

class CalculatorPresenter(private val view: CalculatorContract.View) : CalculatorContract.Presenter {
    private val calculator = Calculator()
    private val expression = Expression("")

    override fun onOperandClicked(operand: String) {
        expression.setOperand(operand)
        view.showExpression(expression.value)
    }

    override fun onOperatorClicked(operator: ArithmeticOperator) {
        expression.setOperator(operator.value)
        view.showExpression(expression.value)
    }

    override fun onEqualsClicked() {
        kotlin.runCatching {
            calculator.calculate(ArithmeticExpression(expression.value)).toString()
        }.onSuccess { result ->
            expression.setEquals(result)
            view.showExpression(expression.value)
        }.onFailure { exception ->
            view.showToast(exception.message ?: "")
        }
    }

    override fun onDeleteClicked() {
        expression.setDelete()
        view.showExpression(expression.value)
    }
}
