package edu.nextstep.camp.calculator

import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorViewModel : ViewModel() {
    private val calculator = Calculator()
    private var expression = Expression.EMPTY

    fun addToExpression(operand: Int) {
        expression += operand
//        view.showExpression(expression)
    }

    fun addToExpression(operator: Operator) {
        expression += operator
//        view.showExpression(expression)
    }

    fun removeLast() {
        expression = expression.removeLast()
//        view.showExpression(expression)
    }

    fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
//            view.showIncompleteExpressionError()
        } else {
            expression = Expression(listOf(result))
//            view.showResult(result)
        }
    }
}
