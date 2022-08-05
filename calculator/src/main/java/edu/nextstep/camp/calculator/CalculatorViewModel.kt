package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.nextstep.camp.calculator.data.historyStorage.HistoryDatabase
import edu.nextstep.camp.calculator.data.historyStorage.HistoryEntity
import edu.nextstep.camp.calculator.data.historyStorage.HistoryManager
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.event.Event
import edu.nextstep.camp.calculator.event.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val historyManager: HistoryManager
) : ViewModel() {

    private val calculator = Calculator()

    private val _expression = MutableLiveData(Expression.EMPTY)
    val expression: LiveData<Expression> = _expression

    private val _showEvent = SingleLiveEvent<Event>()
    val showEvent: LiveData<Event> = _showEvent

    private val _history = MutableLiveData<List<HistoryEntity>>()
    val history: LiveData<List<HistoryEntity>> = _history

    private val _isHistoryVisible = MutableLiveData<Boolean>(false)
    val isHistoryVisible: LiveData<Boolean> = _isHistoryVisible

    fun addOperator(operator: Operator) {
        _expression.value = currentExpression() + operator
    }

    fun addOperand(operand: Int) {
        _expression.value = currentExpression() + operand
    }

    fun removeLast() {
        _expression.value = currentExpression().removeLast()
    }

    fun calculate() {
        val expression: String = currentExpression().toString()
        val result = calculator.calculate(expression)

        if (result == null) {
            _showEvent.value = Event.Error("완성되지 않은 수식입니다.")
            return
        }

        _expression.value = Expression.EMPTY + result
        saveHistory(expression, result)
    }

    fun toggleHistory() {
        val isVisible = _isHistoryVisible.value ?: false
        if (!isVisible) {
            getHistory()
        }

        _isHistoryVisible.value = !isVisible
    }

    private fun currentExpression(): Expression {
        return _expression.value ?: Expression.EMPTY
    }

    private fun saveHistory(expression: String, result: Int) {
        viewModelScope.launch {
            historyManager.insert(expression, result)
        }
    }

    private fun getHistory() {
        viewModelScope.launch {
            _history.value = historyManager.getAll()
        }
    }
}