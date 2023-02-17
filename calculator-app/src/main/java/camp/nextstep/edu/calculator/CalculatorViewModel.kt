package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression

class CalculatorViewModel : ViewModel() {

    private val calculator = Calculator()

    private val _expression = MutableLiveData<Expression>(Expression.EMPTY)
    val expression: LiveData<Expression> = _expression

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> = _result

    private val _showIncompleteExpressionError: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val showIncompleteExpressionError: LiveData<Boolean> = _showIncompleteExpressionError

    fun addToExpression(operand: Int) {
        var subExpression = _expression.value
        subExpression = subExpression?.plus(operand)
        subExpression?.let { showExpression(it) }
    }

    fun removeLast() {
        var subExpression = _expression.value
        subExpression = subExpression?.removeLast()
        subExpression?.let { showExpression(it) }
    }

    fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            _showIncompleteExpressionError.value = true
        } else {
            _expression.value = Expression(listOf(result))
            showResult(result)
        }
    }

    private fun showExpression(expression: Expression) {
        _expression.value = expression
    }

    private fun showResult(result: Int) {
        _result.value = result.toString()
    }
}
