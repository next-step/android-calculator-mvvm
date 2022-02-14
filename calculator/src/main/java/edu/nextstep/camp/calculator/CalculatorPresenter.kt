package edu.nextstep.camp.calculator

class CalculatorPresenter(
    private val view: CalculatorContract.View
) : CalculatorContract.Presenter {
    private val calculator = Calculator()
    private var expression = Expression.EMPTY

    override fun addToExpression(operand: Int) {
        expression += operand
        view.showExpression(expression)
    }

    override fun addToExpression(operator: Operator) {
        expression += operator
        view.showExpression(expression)
    }

    override fun removeLast() {
        expression = expression.removeLast()
        view.showExpression(expression)
    }

    override fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            view.showIncompleteExpressionError()
        } else {
            expression = Expression(listOf(result))
            view.showResult(result)
        }
    }
}
