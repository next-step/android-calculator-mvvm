package edu.nextstep.camp.calculator

interface CalculatorContract {
    interface View {
        var presenter: Presenter

        fun showExpression(expression: Expression)
        fun showResult(result: Int)
        fun showIncompleteExpressionError()
    }

    interface Presenter {
        fun addToExpression(operand: Int)
        fun addToExpression(operator: Operator)
        fun calculate()
        fun removeLast()
    }
}
