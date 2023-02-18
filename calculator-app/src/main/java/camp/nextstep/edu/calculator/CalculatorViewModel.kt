package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator

class CalculatorViewModel : ViewModel() {

    private val calculator = Calculator()

    private val _expression = MutableLiveData<Expression>(Expression.EMPTY)
    val expression: LiveData<Expression> = _expression

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> = _result

    private val _showIncompleteExpressionError: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val showIncompleteExpressionError: LiveData<Boolean> = _showIncompleteExpressionError

    fun addToExpression(operand: Int) {
        val expression = _expression.value ?: Expression.EMPTY
        showExpression(expression.plus(operand))
    }

    fun addToExpression(operator: Operator) {
        val expression = _expression.value ?: return
        showExpression(expression.plus(operator))
    }

    fun removeLast() {
        val expression = _expression.value ?: return
        showExpression(expression.removeLast())
    }

    fun calculate() {
        val result = calculator.calculate(_expression.value.toString())
        if (result == null) {
            _showIncompleteExpressionError.value = true
        } else {
            val expression = Expression(listOf(result))
            showExpression(expression)
            _showIncompleteExpressionError.value = false
        }
    }

    fun showExpression(expression: Expression) {
        _expression.value = expression
    }
}
