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

    private val _text = MutableLiveData<String>()
    val text: LiveData<String>
        get() = _text

    private val _showErrorMessage = MutableLiveData<Event<Unit>>()
    val showErrorMessage: LiveData<Event<Unit>> = _showErrorMessage

    fun addToExpression(operand: Int) {
        expression += operand
        _text.value = expression.toString()
    }

    fun addToExpression(operator: Operator) {
        expression += operator
        _text.value = expression.toString()
    }

    fun removeLast() {
        expression = expression.removeLast()
        _text.value = expression.toString()
    }

    fun calculator() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            _showErrorMessage.value = Event(Unit)
            return
        }
        _text.value = result.toString()
    }


}