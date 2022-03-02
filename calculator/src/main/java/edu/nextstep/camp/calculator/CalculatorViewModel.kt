package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Memories
import edu.nextstep.camp.calculator.domain.Memory
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorViewModel(
    private val calculator: Calculator = Calculator()
) : ViewModel() {
    private val _memories = MutableLiveData(Memories())
    val memories: LiveData<Memories> = _memories

    private val _isMemoryVisible = MutableLiveData(false)
    val isMemoryVisible: LiveData<Boolean> = _isMemoryVisible

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
        val memories = _memories.value ?: return
        val expression = _expression.value ?: return
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            _onCalculationErrorEvent.value = Event.CalculationErrorEvent
            return
        }
        _expression.value = Expression.EMPTY + result
        _memories.value = memories + Memory(expression, result)
    }

    fun toggleMemory() {
        val isMemoryVisible = _isMemoryVisible.value ?: return
        _isMemoryVisible.value = !isMemoryVisible
    }
}
