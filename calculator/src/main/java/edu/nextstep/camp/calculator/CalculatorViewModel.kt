package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.nextstep.camp.data.Memory
import edu.nextstep.camp.data.MemoryDao
import edu.nextstep.camp.domain.Calculator
import edu.nextstep.camp.domain.Expression
import edu.nextstep.camp.domain.Operator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CalculatorViewModel(
    private val calculator: Calculator = Calculator(),
    private val memoryDao: MemoryDao,
) : ViewModel() {

    private val _expressionEvent = SingleLiveEvent<Expression>()
    val expressionEvent: LiveData<Expression> get() = _expressionEvent

    private val _errorEvent = SingleLiveEvent<Unit>()
    val errorEvent: LiveData<Unit> get() = _errorEvent

    private val _viewTypeEvent = SingleLiveEvent<CalculatorViewType>()
    val viewTypeEvent: LiveData<CalculatorViewType> get() = _viewTypeEvent

    private val currentExpression: Expression get() = _expressionEvent.value ?: Expression.EMPTY
    private val viewType: CalculatorViewType get() = _viewTypeEvent.value ?: ExpressionView

    fun addToExpression(operand: Int) {
        val newExpression = currentExpression + operand
        _expressionEvent.value = newExpression
    }

    fun addToExpression(operator: Operator) {
        val newExpression = currentExpression + operator
        _expressionEvent.value = newExpression
    }

    fun removeLast() {
        val newExpression = currentExpression.removeLast()
        _expressionEvent.value = newExpression
    }

    fun calculate() {
        val result = calculator.calculate(currentExpression.toString())
        if (result == null) {
            _errorEvent.call()
            return
        }

        saveExpression(currentExpression, result)
        _expressionEvent.value = Expression(listOf(result))
    }

    fun toggleViewType() = viewModelScope.launch {
        val memories = if (viewType.isExpression()) getMemories() else null
        _viewTypeEvent.postValue(viewType.toggle(memories))
    }

    private suspend fun getMemories(): List<Memory> = withContext(Dispatchers.IO) {
        memoryDao.getAll()
    }

    private fun saveExpression(expression: Expression, result: Int) = viewModelScope.launch {
        val memory = Memory(expression.toString(), result.toString())
        memoryDao.insert(memory)
    }
}