package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    private val calculator = Calculator()

    val eventShowIncompleteExpressionError = SingleLiveEvent<Unit>()

    private val _expression = MutableLiveData(Expression.EMPTY)
    val expression: LiveData<Expression> = _expression

    private val expressionNotNull get() = _expression.value ?: Expression.EMPTY

    fun addToExpression(operand: Int) {
        _expression.postValue(expressionNotNull.plus(operand))
    }

    fun addToExpression(operator: Operator) {
        _expression.postValue(expressionNotNull.plus(operator))
    }

    fun calculate() {
        val result = calculator.calculate(expression.value.toString())
        if (result == null) {
            showIncompleteExpressionError()
            return
        }
        _expression.postValue(Expression(listOf(result)))
    }

    fun removeLast() {
        _expression.value?.let {
            _expression.postValue(it.removeLast())
        }
    }

    private fun showIncompleteExpressionError() {
        eventShowIncompleteExpressionError.call()
    }
}