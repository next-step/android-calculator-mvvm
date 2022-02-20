package edu.nextstep.camp.calculator

interface CalculatorContract {
    interface View {
        var presenter: Presenter

        fun showExpression(expression: edu.nextstep.camp.domain.Expression)
        fun showResult(result: Int)
        fun showIncompleteExpressionError()
    }

    interface Presenter {
        fun addToExpression(operand: Int)
        fun addToExpression(operator: edu.nextstep.camp.domain.Operator)
        fun calculate()
        fun removeLast()
    }
}
