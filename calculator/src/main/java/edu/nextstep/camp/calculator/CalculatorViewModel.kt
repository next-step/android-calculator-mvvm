package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorViewModel : ViewModel() {

    private var expression = Expression.EMPTY
    private val calculator = Calculator()

    private val _display: MutableLiveData<String> = MutableLiveData()
    val display: LiveData<String> = _display

    private val _event: MutableLiveData<Event<ViewEvent>> = MutableLiveData()
    val event: LiveData<Event<ViewEvent>> = _event

    fun addOperand(operand: Int) {
        expression += operand
        _display.value = expression.toString()
    }

    fun addOperator(operator: Operator) {
        expression += operator
        _display.value = expression.toString()
    }

    fun removeLast() {
        expression = expression.removeLast()
        _display.value = expression.toString()
    }

    fun calculate() {
        val result = calculator.calculate(expression.toString())

        if (result == null) {
            _event.value = Event(ViewEvent.IncompleteExpressionError)
            return
        }

        expression = Expression.EMPTY + result
        _display.value = expression.toString()
    }

    sealed class ViewEvent {
        object IncompleteExpressionError : ViewEvent()
    }
}