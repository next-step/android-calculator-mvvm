package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorViewModel(
    private val calculator: Calculator = Calculator(),
    expression: Expression = Expression.EMPTY
) : ViewModel() {

    private val _expression = MutableLiveData<Expression>(expression)
    val expression: LiveData<Expression>
        get() = _expression

    private val _expressionError = MutableLiveData<Event<Unit>>()
    val expressionError: LiveData<Event<Unit>>
        get() = _expressionError

    fun addToExpression(operand: Int) {
        _expression.value = _expression.value?.plus(operand)
    }

    fun addToExpression(operator: Operator) {
        _expression.value = _expression.value?.plus(operator)
    }

    fun removeLast() {
        _expression.value = expression.value?.removeLast()
    }

    fun calculate() {
        val result = calculator.calculate(expression.value.toString())
        if (result == null) {
            _expressionError.value = Event(Unit)
        } else {
            _expression.value = Expression(listOf(result))
        }
    }
}