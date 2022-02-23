package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.data.AppDatabase
import edu.nextstep.camp.calculator.data.ResultRecord
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Event
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorViewModel(
    private var expression: Expression,
    private var calculator: Calculator
) : ViewModel() {
    private var _statement = MutableLiveData(expression.toString())
        val statement: LiveData<String>
            get() = _statement

    private val _result = MutableLiveData<Event<Int?>>()
        val result: LiveData<Event<Int?>>
            get() = _result

    private val _showErrorMessage = MutableLiveData<Event<Unit>>()
        val showErrorMessage: LiveData<Event<Unit>>
            get() = _showErrorMessage

    private val _isMemoryVisible = MutableLiveData(false)
        val isMemoryVisible: LiveData<Boolean>
            get() = _isMemoryVisible

    fun addToExpression(operand: Int) {
        if (isMemoryVisible.value == true) {
            return
        }
        expression += operand
        _statement.value = expression.toString()
    }

    fun addToExpression(operator: Operator) {
        if (isMemoryVisible.value == true) {
            return
        }
        expression += operator
        _statement.value = expression.toString()
    }

    fun erase() {
        if (isMemoryVisible.value == true) {
            return
        }
        expression = expression.removeLast()
        _statement.value = expression.toString()
    }

    fun equals() {
        if (isMemoryVisible.value == true || _statement.value == null) {
            return
        }

        val calcResultValue: Int? = calculator.calculate(_statement.value!!)

        if (calcResultValue == null) {
            _showErrorMessage.value = Event(Unit)
            return
        }

        _result.value = Event(calcResultValue)

        expression = Expression(listOf(calcResultValue))
        _statement.value = expression.toString()
    }

    fun memory() {
        _isMemoryVisible.value = !_isMemoryVisible.value!!
    }
}