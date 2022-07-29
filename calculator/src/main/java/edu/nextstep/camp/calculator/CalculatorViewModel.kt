package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorViewModel : ViewModel() {
    private val calculator = Calculator()
    private var expression = Expression.EMPTY

    private val _result = SingleLiveEvent<String>()
    val result: LiveData<String>
        get() = _result

    private val _error = SingleLiveEvent<CalculatorErrorEvent>()
    val error: LiveData<CalculatorErrorEvent>
        get() = _error

    fun clickNumberButton(operand: Int) {
        expression += operand
        _result.value = expression.toString()
    }

    fun clickOperatorButton(operator: Operator) {
        expression += operator
        _result.value = expression.toString()
    }

    fun clickDeleteButton() {
        expression = expression.removeLast()
        _result.value = expression.toString()
    }

    fun clickEqualButton() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            _error.value = CalculatorErrorEvent.IncompleteExpressionError
        } else {
            expression = Expression(listOf(result))
            _result.value = expression.toString()
        }
    }
}