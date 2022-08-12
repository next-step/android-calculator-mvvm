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
    val expression: LiveData<Expression> = _expression

    private val _errorMessage = SingleLiveEvent<UiText>()
    val errorMessage: LiveData<UiText> = _errorMessage

    fun addToExpression(number: Int) {
        _expression.value = getCurrentExpression() + number
    }

    fun addToExpression(operator: Operator) {
        _expression.value = getCurrentExpression() + operator
    }

    fun removeLast() {
        _expression.value = getCurrentExpression().removeLast()
    }

    fun calculate() {
        val rawExpressionString = getCurrentExpression().toString()
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

    private fun getCurrentExpression() = _expression.value ?: Expression.EMPTY
}