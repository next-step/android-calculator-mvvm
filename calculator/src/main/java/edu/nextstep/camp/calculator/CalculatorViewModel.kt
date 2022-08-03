package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression

class CalculatorViewModel : ViewModel() {
    private val calculator = Calculator()
    private var expression = Expression.EMPTY

    private val _result = MutableLiveData<String>()
    val result: LiveData<String>
        get() = _result

    private val _showErrorMessage = MutableLiveData<Event<Any>>()
    val showErrorMessage: LiveData<Event<Any>>
        get() = _showErrorMessage

    fun addToExpression(operand: Int) {
        expression += operand
        _result.value = expression.toString()
    }

    fun addToExpression(operator: edu.nextstep.camp.calculator.domain.Operator) {
        expression += operator
        _result.value = expression.toString()
    }

    fun removeLast() {
        expression = expression.removeLast()
        _result.value = expression.toString()
    }

    fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            _showErrorMessage.value = Event<Any>("제대로 된 수식을 입력해주세요")
        } else {
            expression = Expression(listOf(result))
            _result.value = expression.toString()
        }
    }
}