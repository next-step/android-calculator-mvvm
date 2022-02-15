package edu.nextstep.camp.calculator

class CalculatorPresenter(
    private val view: CalculatorContract.View
) : CalculatorContract.Presenter {
    private val calculator = edu.nextstep.camp.domain.calculator.Calculator()
    private var expression = edu.nextstep.camp.domain.calculator.Expression.EMPTY

    override fun addToExpression(operand: Int) {
        expression += operand
        view.showExpression(expression)
    }

    override fun addToExpression(operator: edu.nextstep.camp.domain.calculator.Operator) {
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
            expression = edu.nextstep.camp.domain.calculator.Expression(listOf(result))
            view.showResult(result)
        }
    }
}
