package edu.nextstep.camp.calculator

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    private val calculator = Calculator()

    val eventShowIncompleteExpressionError = SingleLiveEvent<Unit>()

    private val expression = MutableLiveData(Expression.EMPTY)

    val expressionResult = MediatorLiveData<String>().apply {
        addSource(expression) {
            this.postValue(it.toString())
        }
    }

    fun addToExpression(operand: Int) {
        expression.value?.let {
            expression.postValue(it.plus(operand))
        }
    }

    fun addToExpression(operator: Operator) {
        expression.value?.let {
            expression.postValue(it.plus(operator))
        }
    }

    fun calculate() {
        val result = calculator.calculate(expression.value.toString())
        if (result == null) {
            showIncompleteExpressionError()
        } else {
            expression.postValue(Expression(listOf(result)))
        }
    }

    fun removeLast() {
        expression.value?.let {
            expression.postValue(it.removeLast())
        }
    }

    private fun showIncompleteExpressionError() {
        eventShowIncompleteExpressionError.call()
    }
}