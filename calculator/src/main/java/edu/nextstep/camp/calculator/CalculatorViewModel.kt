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
    private var lastExpression: Expression = Expression.EMPTY
) : ViewModel() {

    private val _expression = MutableLiveData(lastExpression)
    val expression: LiveData<Expression>
        get() = _expression

    private val _event = MutableLiveData<Event<CalculatorEvent>>()
    val event: LiveData<Event<CalculatorEvent>>
        get() = _event

    fun addOperandToExpression(operand: Int) {
        lastExpression += operand
        _expression.value = lastExpression
    }

    fun addOperatorToExpression(operator: Operator) {
        lastExpression += operator
        _expression.value = lastExpression
    }

    fun removeLastFromExpression() {
        lastExpression = lastExpression.removeLast()
        _expression.value = lastExpression
    }

    fun requestCalculate() {
        val result = calculator.calculate(lastExpression.toString())
        if (result == null || lastExpression.isSameValue(result)) {
            _event.value = Event(ERROR_INCOMPLETE_EXPRESSION)
            return
        }
        lastExpression = Expression.EMPTY + result
        _expression.value = lastExpression
    }

}