package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.CalculatorEvent.*
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorViewModel(
    private val calculator: Calculator = Calculator(),
    lastExpression: Expression = Expression.EMPTY
) : ViewModel() {

    private val _expression = MutableLiveData(lastExpression)
    val expression: LiveData<Expression>
        get() = _expression

    private val _event = MutableLiveData<Event<CalculatorEvent>>()
    val event: LiveData<Event<CalculatorEvent>>
        get() = _event

    fun addOperandToExpression(operand: Int) {
        val newExpression = getExpressionOrEmpty() + operand
        _expression.value = newExpression
    }

    fun addOperatorToExpression(operator: Operator) {
        val newExpression = getExpressionOrEmpty() + operator
        _expression.value = newExpression
    }

    fun removeLastFromExpression() {
        val newExpression = getExpressionOrEmpty().removeLast()
        _expression.value = newExpression
    }

    fun requestCalculate() {
        val inputtedExpression = getExpressionOrEmpty()
        val result = calculator.calculate(inputtedExpression.toString())
        if (result == null || inputtedExpression.isSameValue(result)) {
            _event.value = Event(ERROR_INCOMPLETE_EXPRESSION)
            return
        }
        val newExpression = Expression.EMPTY + result
        _expression.value = newExpression
    }

    private fun getExpressionOrEmpty(): Expression = expression.value ?: Expression.EMPTY
}