package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorViewModel(
    private var expression: Expression = Expression.EMPTY,
    private val calculator: Calculator = Calculator()
) : ViewModel() {

    private val _showingExpression = MutableLiveData<String>()
    val showingExpression: LiveData<String>
        get() = _showingExpression

    private val _calculatorError = SingleLiveEvent<Event<Unit>>()
    val calculatorError: LiveData<Event<Unit>> = _calculatorError

    fun addToExpression(operand: Int) {
        expression += operand
        _showingExpression.value = expression.toString()
    }

    fun addToExpression(operator: Operator) {
        expression += operator
        _showingExpression.value = expression.toString()
    }

    fun removeLast() {
        expression = expression.removeLast()
        _showingExpression.value = expression.toString()
    }

    fun calculate() {
        val result = calculator.calculate(expression.toString())

        if (result != null) {
            expression = Expression(listOf(result))
            _showingExpression.value = expression.toString()
        } else {
            _calculatorError.value = Event(Unit)
        }
    }
}
