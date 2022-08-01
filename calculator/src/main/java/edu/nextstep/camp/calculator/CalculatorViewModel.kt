package edu.nextstep.camp.calculator

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.nextstep.camp.calculator.common.SingleLiveEvent
import edu.nextstep.camp.calculator.memoryview.MemoryUIModel
import edu.nextstep.camp.data.Log
import edu.nextstep.camp.data.LogDatabase
import edu.nextstep.camp.domain.Calculator
import edu.nextstep.camp.domain.Expression
import edu.nextstep.camp.domain.Operator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(private val db: LogDatabase, expression: Expression) : ViewModel() {
    private val calculator = Calculator()

    private var isMemoryViewDisplayed = false

    private val _visibilityMemoryView =MutableLiveData(View.GONE)
    val visibilityMemoryView: LiveData<Int>
        get() = _visibilityMemoryView

    private val _memoryLog = MutableLiveData(listOf<MemoryUIModel>())
    val memoryLog: LiveData<List<MemoryUIModel>>
        get() = _memoryLog

    private val _result = MutableLiveData(expression)
    val result: LiveData<Expression>
        get() = _result

    private val _error = SingleLiveEvent<CalculatorErrorEvent>()
    val error: LiveData<CalculatorErrorEvent>
        get() = _error

    fun addOperandToExpression(operand: Int) {
        _result.value = _result.value?.plus(operand)
    }

    fun addOperatorToExpression(operator: Operator) {
        _result.value = _result.value?.plus(operator)
    }

    fun deleteExpression() {
        _result.value = _result.value?.removeLast() ?: Expression.EMPTY
    }

    fun calculateExpression() {
        val result = calculator.calculate(_result.value?.toString() ?: Expression.EMPTY.toString())
        if (result == null) {
            _error.value = CalculatorErrorEvent.IncompleteExpressionError
            return
        }
        val completeExpression = _result.value
        _result.value = Expression(listOf(result))
        saveExpression(completeExpression.toString(), result.toString())
    }

    private fun saveExpression(expression: String, result: String) {
        CoroutineScope(Dispatchers.IO).launch {
            db.logDao().insert(Log(expression, result))
        }
    }

    fun showMemoryView() {
        isMemoryViewDisplayed = isMemoryViewDisplayed.not()
        if (isMemoryViewDisplayed) {
            _visibilityMemoryView.value = View.VISIBLE
            _result.value = Expression.EMPTY
            fetchMemoryList()
        } else {
            _visibilityMemoryView.value = View.GONE
            getLastLog()
        }
    }

    private fun fetchMemoryList() {
        CoroutineScope(Dispatchers.IO).launch {
            val list = getLogs()
            _memoryLog.postValue(mapToMemoryUIModel(list))
        }
    }

    private fun getLogs(): List<Log> = db.logDao().getAll()

    private fun getLastLog() {
        CoroutineScope(Dispatchers.IO).launch {
            val log = db.logDao().getLast()
            _result.postValue(Expression(listOf(log.result.toInt())))
        }
    }

    private fun mapToMemoryUIModel(logs: List<Log>): List<MemoryUIModel> {
        val memoryUIModel = mutableListOf<MemoryUIModel>()
        for (log in logs) {
            memoryUIModel.add(MemoryUIModel(log.id, log.expressionText, log.result))
        }
        return memoryUIModel.toList()
    }
}