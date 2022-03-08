package edu.nextstep.camp.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Memories
import edu.nextstep.camp.calculator.domain.Memory
import edu.nextstep.camp.calculator.domain.MemoryRepository
import edu.nextstep.camp.calculator.domain.Operator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val calculator: Calculator,
    private val memoryRepository: MemoryRepository
) : ViewModel() {
    val memories: Flow<Memories> = memoryRepository.getMemories()

    private val _isMemoryVisible = MutableStateFlow(false)
    val isMemoryVisible: StateFlow<Boolean> = _isMemoryVisible

    private val _expression = MutableStateFlow(Expression.EMPTY)
    private val _text = MutableStateFlow("")
    val text: StateFlow<String> = _text.asStateFlow()

    private val _onCalculationErrorEvent = MutableStateFlow<Event>(Event.CalculationErrorEvent)
    val onCalculationErrorEvent: StateFlow<Event> = _onCalculationErrorEvent

    init {
        viewModelScope.launch {
            _expression.collect {
                _text.value = _expression.value.toString()
            }
        }
    }

    fun addToExpression(operand: Int) {
        val expression = _expression.value
        _expression.value = expression + operand
    }

    fun addToExpression(operator: Operator) {
        val expression = _expression.value
        _expression.value = expression + operator
    }

    fun removeLast() {
        val expression = _expression.value
        _expression.value = expression.removeLast()
    }

    fun calculate() {
        val expression = _expression.value
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            _onCalculationErrorEvent.value = Event.CalculationErrorEvent
            return
        }
        _expression.value = Expression.EMPTY + result
        viewModelScope.launch {
            memoryRepository.addMemory(Memory(expression, result))
        }
    }

    fun toggleMemory() {
        val isMemoryVisible = _isMemoryVisible.value
        _isMemoryVisible.value = !isMemoryVisible
    }
}
