package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.domain.calculator.Calculator
import edu.nextstep.camp.domain.calculator.Expression
import edu.nextstep.camp.domain.calculator.Operator

class CalculatorViewModel: ViewModel() {
    private val calculator = Calculator()
    private val _expression = MutableLiveData(Expression.EMPTY)
    val expression: LiveData<Expression> get() = _expression

    private val _errorMessage = SingleLiveEvent<UiText>()
    val errorMessage: LiveData<UiText> get() = _errorMessage

    fun addToExpression(number: Int) {
        _expression.value = _expression.value?.plus(number) ?: Expression.EMPTY
    }

    fun addToExpression(operator: Operator) {
        _expression.value = _expression.value?.plus(operator) ?: Expression.EMPTY
    }

    fun removeLast() {
        _expression.value = _expression.value?.removeLast() ?: Expression.EMPTY
    }

    fun calculate() {
        val rawExpressionString = _expression.value?.toString() ?: ""
        val result = calculator.calculate(rawExpressionString)
        if (result == null) {
            showIncompleteExpressionError()
        } else {
            _expression.value = Expression(listOf(result))
        }
    }

    private fun showIncompleteExpressionError() {
        _errorMessage.value = UiText.StringResource(R.string.incomplete_expression)
    }
}