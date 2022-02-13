package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorViewModel : ViewModel() {

    private val _displayExpression = MutableLiveData<Expression>()
    val displayExpression: LiveData<Expression>
        get() = _displayExpression

    private val _displayResult = MutableLiveData<Int>()
    val displayResult: LiveData<Int>
        get() = _displayResult

    private val _showIncompleteExpressionError = SingleLiveEvent<Unit>()
    val showIncompleteExpressionError: LiveData<Unit>
        get() = _showIncompleteExpressionError

    private val calculator = Calculator()
    private var expression = Expression.EMPTY

    fun addToExpression(operand: Int) {
        expression += operand
        _displayExpression.value = expression
    }

    fun addToExpression(operator: Operator) {
        expression += operator
        _displayExpression.value = expression
    }

    fun removeLast() {
        expression = expression.removeLast()
        _displayExpression.value = expression
    }

    fun calculate() {
        val result = calculator.calculate(expression.toString()) ?: run {
            _showIncompleteExpressionError.value = Unit
            return
        }

        expression = Expression(listOf(result))
        _displayResult.value = result
    }
}