package camp.nextstep.edu.calculator

import camp.nextstep.edu.calculator.domain.ArithmeticOperator

interface CalculatorContract {

    interface View {
        var presenter: Presenter

        fun showExpression(expression: String)
        fun showToast(message: String)
    }

    interface Presenter {
        fun onOperandClicked(operand: String)
        fun onOperatorClicked(operator: ArithmeticOperator)
        fun onEqualsClicked()
        fun onDeleteClicked()
    }
}
