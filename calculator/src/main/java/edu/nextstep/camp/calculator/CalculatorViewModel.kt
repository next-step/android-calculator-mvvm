package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorViewModel(
    private val calculator: Calculator = Calculator()
) : ViewModel() {
    private val _expression = MutableLiveData(Expression.EMPTY)
    val text: LiveData<String> = Transformations.map(_expression) { it.toString() }

    private val _onCalculationErrorEvent = MutableLiveData<Event>()
    val onCalculationErrorEvent: LiveData<Event> = _onCalculationErrorEvent

    fun addToExpression(operand: Int) {
        val expression = _expression.value ?: return
        _expression.value = expression + operand
    }

    fun addToExpression(operator: Operator) {
        val expression = _expression.value ?: return
        _expression.value = expression + operator
    }

    fun removeLast() {
        val expression = _expression.value ?: return
        _expression.value = expression.removeLast()
    }

    fun calculate() {
        val expression = _expression.value ?: return
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            _onCalculationErrorEvent.value = Event.CalculationErrorEvent
            return
        }
        _expression.value = Expression.EMPTY + result
    }
}
