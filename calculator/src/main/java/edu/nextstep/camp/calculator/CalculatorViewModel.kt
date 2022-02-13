package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorViewModel : ViewModel() {

    private val _displayResult = MutableLiveData<String>()
    val displayResult: LiveData<String>
        get() = _displayResult

    private val _showIncompleteExpressionError = SingleLiveEvent<Unit>()
    val showIncompleteExpressionError: LiveData<Unit>
        get() = _showIncompleteExpressionError

    private val calculator = Calculator()
    private var expression = Expression.EMPTY

    fun addToExpression(operand: Int) {
        expression += operand
        _displayResult.value = expression.toString()
    }

    fun addToExpression(operator: Operator) {
        expression += operator
        _displayResult.value = expression.toString()
    }

    fun removeLast() {
        expression = expression.removeLast()
        _displayResult.value = expression.toString()
    }

    fun calculate() {
        val result = calculator.calculate(expression.toString()) ?: run {
            _showIncompleteExpressionError.value = Unit
            return
        }

        expression = Expression(listOf(result))
        _displayResult.value = result.toString()
    }
}