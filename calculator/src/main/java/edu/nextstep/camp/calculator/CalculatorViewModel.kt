package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorViewModel : ViewModel() {
    private val calculator = Calculator()

    private val _expression = MutableLiveData<Expression>()
    val expression: LiveData<Expression>
        get() = _expression

    private val _errorEvent = SingleLiveEvent<Event>()
    val errorEvent: LiveData<Event>
        get() = _errorEvent

    private fun getExpressionValue() = _expression.value ?: Expression.EMPTY

    fun addToExpression(operand: Int) {
        _expression.value = getExpressionValue() + operand
    }

    fun addToExpression(operator: Operator) {
        _expression.value = getExpressionValue() + operator
    }

    fun removeLast() {
        _expression.value = getExpressionValue().removeLast()
    }

    fun calculate() {
        val result = calculator.calculate(expression.value.toString())
        if (result == null) {
            _errorEvent.value = Event.CalculatorError
        } else {
            _expression.value = Expression(listOf(result))
        }
    }
}
