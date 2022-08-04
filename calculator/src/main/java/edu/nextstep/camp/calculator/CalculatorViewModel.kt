package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.nextstep.camp.calculator.common.SingleLiveEvent
import edu.nextstep.camp.calculator.memoryview.MemoryUIModel
import edu.nextstep.camp.data.LogEntity
import edu.nextstep.camp.data.LogRepository
import edu.nextstep.camp.domain.Calculator
import edu.nextstep.camp.domain.Expression
import edu.nextstep.camp.domain.Operator
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(private val repository: LogRepository, initExpression: Expression) : ViewModel() {
    private val calculator = Calculator()

    private var _isMemoryViewDisplayed = MutableLiveData(false)
    val isMemoryViewDisplayed: LiveData<Boolean>
        get() = _isMemoryViewDisplayed

    private val _memoryLogs = MutableLiveData(listOf<MemoryUIModel>())
    val memoryLogs: LiveData<List<MemoryUIModel>>
        get() = _memoryLogs

    private val _expression = MutableLiveData(initExpression)
    val expression: LiveData<Expression>
        get() = _expression

    private val _error = SingleLiveEvent<CalculatorErrorEvent>()
    val error: LiveData<CalculatorErrorEvent>
        get() = _error

    fun addOperandToExpression(operand: Int) {
        _expression.value = _expression.value?.plus(operand)
    }

    fun addOperatorToExpression(operator: Operator) {
        _expression.value = _expression.value?.plus(operator)
    }

    fun deleteExpression() {
        _expression.value = _expression.value?.removeLast() ?: Expression.EMPTY
    }

    fun calculateExpression() {
        val result = calculator.calculate(_expression.value?.toString() ?: Expression.EMPTY.toString())
        if (result == null) {
            _error.value = CalculatorErrorEvent.IncompleteExpressionError
            return
        }
        val completeExpression = _expression.value
        _expression.value = Expression(listOf(result))
        saveExpression(completeExpression.toString(), result.toString())
    }

    private fun saveExpression(expression: String, result: String) {
        viewModelScope.launch {
            repository.insertLog(LogEntity(expression, result))
        }
    }

    fun controlMemoryView() {
        _isMemoryViewDisplayed.value = _isMemoryViewDisplayed.value?.not()
        if (isMemoryViewDisplayed.value == true) {
            fetchMemoryList()
        }
    }

    private fun fetchMemoryList() {
        viewModelScope.launch {
            val list = repository.getLogs()
            _memoryLogs.postValue(mapToMemoryUIModel(list))
        }
    }

    private fun mapToMemoryUIModel(logs: List<LogEntity>): List<MemoryUIModel> {
        return logs.map {
            MemoryUIModel(it.id, it.expressionText, it.result)
        }
    }
}