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

    private val _text = MutableLiveData("")
    val text: LiveData<String> = _text

    private val _onCalculationErrorEvent = SingleLiveEvent<Unit>()
    val onCalculationErrorEvent: LiveData<Unit> = _onCalculationErrorEvent

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

    fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            _onCalculationErrorEvent.call()
            return
        }
        expression = Expression.EMPTY + result
        _text.value = expression.toString()
    }
}
