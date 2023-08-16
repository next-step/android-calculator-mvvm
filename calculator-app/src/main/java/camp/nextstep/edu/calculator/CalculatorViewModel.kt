package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator
import camp.nextstep.edu.calculator.domain.data.Memory
import camp.nextstep.edu.calculator.domain.repository.MemoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val dispatchers: CoroutineDispatcher = Dispatchers.IO,
    private val memoryRepository: MemoryRepository
) : ViewModel() {

    private val _expression = MutableLiveData(Expression.EMPTY)
    val expression: LiveData<Expression>
        get() = _expression

    private val _event = MutableLiveData<EventType>()
    val event: LiveData<EventType>
        get() = _event

    private val _memoryVisible = MutableLiveData(false)
    val memoryVisible: LiveData<Boolean>
        get() = _memoryVisible

    private val _memoryItems = MutableLiveData<List<Memory>>()
    val memoryItems: LiveData<List<Memory>>
        get() = _memoryItems

    init {
        viewModelScope.launch(dispatchers) {
            val items: List<Memory> = memoryRepository.getMemoryList()
            _memoryItems.postValue(items)
        }
    }


    fun addToExpression(operand: Int) {
        val expressionValue = _expression.value ?: return
        _expression.value = expressionValue + operand
    }

    fun addToExpression(operator: Operator) {
        val expressionValue = _expression.value ?: return
        _expression.value = expressionValue + operator
    }

    fun removeLast() {
        val expressionValue = _expression.value ?: return
        _expression.value = expressionValue.removeLast()
    }

    fun calculate() {
        val expressionValue = _expression.value ?: return

        val result = Calculator().calculate(expressionValue.toString())
        result?.let {
            addMemory(Memory(expressionValue.toString(), it.toString()))
            _expression.value = Expression(listOf(it))
        } ?: run {
            _event.value = EventType.SHOW_TOAST
        }
    }

    private fun addMemory(memory: Memory) {
        viewModelScope.launch(dispatchers) {
            memoryRepository.addMemory(memory)
            val items: List<Memory> = memoryRepository.getMemoryList()
            _memoryItems.postValue(items)
        }
    }

    fun showMemory() {
        val visible = _memoryVisible.value ?: return
        _memoryVisible.value = !visible
    }

    enum class EventType {
        SHOW_TOAST
    }
}
