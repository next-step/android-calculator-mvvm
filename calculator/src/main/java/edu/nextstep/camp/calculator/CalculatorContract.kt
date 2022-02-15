package edu.nextstep.camp.calculator

import edu.nextstep.camp.domain.calculator.Expression

interface CalculatorContract {
    interface View {
        var presenter: Presenter

        fun showExpression(expression: Expression)
        fun showResult(result: Int)
        fun showIncompleteExpressionError()
    }

    interface Presenter {
        fun addToExpression(operand: Int)
        fun addToExpression(operator: edu.nextstep.camp.domain.calculator.Operator)
        fun calculate()
        fun removeLast()
    }
}
