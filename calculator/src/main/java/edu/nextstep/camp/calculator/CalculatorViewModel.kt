package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorViewModel : ViewModel() {
    private val calculator = Calculator()

    private val _expression = MutableLiveData(Expression.EMPTY)
    val expression: LiveData<Expression> get() = _expression

    private val _eventShowIncompleteExpressionError = MutableLiveData<Event<Unit>>()
    val eventShowIncompleteExpressionError: LiveData<Event<Unit>> get() = _eventShowIncompleteExpressionError

    fun addToExpression(operand: Int) {
        val previousExpression = expression.value ?: return
        _expression.value = previousExpression + operand
    }

    fun addToExpression(operator: Operator) {
        val previousExpression = expression.value ?: return
        _expression.value = previousExpression + operator
    }

    fun removeLast() {
        _expression.value = expression.value?.removeLast()
    }

    fun calculate() {
        val result = calculator.calculate(expression.value.toString())
        if (result == null) {
            _eventShowIncompleteExpressionError.value = Event(Unit)
        } else {
            _expression.value = Expression(listOf(result))
        }
    }
}
