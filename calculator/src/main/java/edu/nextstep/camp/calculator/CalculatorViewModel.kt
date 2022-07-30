package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorViewModel : ViewModel() {
    private val calculator = Calculator()
    private var expression = Expression.EMPTY

    private val _calculatorText = MutableLiveData("")
    val calculatorText: LiveData<String>
        get() = _calculatorText

    private val _showIncompleteExpressionError = MutableLiveData<Unit>()
    val showIncompleteExpressionError: LiveData<Unit>
        get() = _showIncompleteExpressionError

    fun addToExpression(operand: Int) {
        expression += operand
        updateCalculatorText(expression.toString())
    }

    fun addToExpression(operator: Operator) {
        expression += operator
        updateCalculatorText(expression.toString())
    }

    fun removeLast() {
        expression = expression.removeLast()
        updateCalculatorText(expression.toString())
    }

    fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            _showIncompleteExpressionError.value = Unit
        } else {
            expression = Expression(listOf(result))
            updateCalculatorText(expression.toString())
        }
    }

    private fun updateCalculatorText(text: String) {
        _calculatorText.value = text
    }
}
