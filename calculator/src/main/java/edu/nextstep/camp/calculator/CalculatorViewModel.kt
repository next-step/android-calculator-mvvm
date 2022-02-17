package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Event
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorViewModel(
    private var expression: Expression,
    private var calculator: Calculator
) : ViewModel() {
    private val _result = MutableLiveData(expression.toString())
        val result: LiveData<String>
            get() = _result

    private val _showErrorMessage = MutableLiveData<Event<Unit>>()
    val showErrorMessage: LiveData<Event<Unit>>
        get() = _showErrorMessage

    fun operandClick(operand: Int) {
        expression += operand
        _result.value = expression.toString()
    }

    fun plus() {
        expression += Operator.Plus
        _result.value = expression.toString()
    }

    fun minus() {
        expression += Operator.Minus
        _result.value = expression.toString()
    }

    fun divide() {
        expression += Operator.Divide
        _result.value = expression.toString()
    }

    fun multiply() {
        expression += Operator.Multiply
        _result.value = expression.toString()
    }

    fun erase() {
        expression = expression.removeLast()
        _result.value = expression.toString()
    }

    fun equals() {
        val res = calculator.calculate(expression.toString())
        if (res == null) {
            _showErrorMessage.value = Event(Unit)
            return
        }
        _result.value = res.toString()
    }

    fun memory() {}
}