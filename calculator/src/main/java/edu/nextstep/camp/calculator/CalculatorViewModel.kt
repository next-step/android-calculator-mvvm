package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.utils.Event
import edu.nextstep.camp.calculator.utils.NonNullLiveData
import edu.nextstep.camp.domain.calculator.Calculator
import edu.nextstep.camp.domain.calculator.Expression
import edu.nextstep.camp.domain.calculator.Operator

class CalculatorViewModel(
    private val initialExpression: Expression = Expression.EMPTY
) : ViewModel() {
    private val calculator = Calculator()

    private val _expression = NonNullLiveData(initialExpression)
    val expression: LiveData<Expression>
        get() = _expression

    private val _incompleteExpressionEvent = MutableLiveData<Event<Boolean>>()
    val incompleteExpressionEvent: LiveData<Event<Boolean>>
        get() = _incompleteExpressionEvent

    fun addToExpression(operand: Int) {
        _expression.value += operand
    }

    fun addToExpression(operator: Operator) {
        _expression.value += operator
    }

    fun removeLast() {
        _expression.value = _expression.value.removeLast()
    }

    fun calculate() {
        val currentExpression = _expression.value
        val result = calculator.calculate(currentExpression.toString())
        if (result != null) {
            _expression.value = Expression(result)
            return
        }
        _incompleteExpressionEvent.value = Event(true)
    }
}
