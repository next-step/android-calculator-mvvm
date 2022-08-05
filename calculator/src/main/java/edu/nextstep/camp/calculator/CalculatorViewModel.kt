package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.nextstep.camp.calculator.domain.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val initExpression: Expression = Expression.EMPTY,
    private val calculator: Calculator,
    private val repository: HistoryRepository
) : ViewModel() {

    private val _expression = MutableLiveData<Expression>()
    val expression: LiveData<Expression>
        get() = _expression

    private val _errorEvent = SingleLiveEvent<Event>()
    val errorEvent: LiveData<Event>
        get() = _errorEvent

    private val _historyList = MutableLiveData<List<History>>()
    val historyList: LiveData<List<History>>
        get() = _historyList

    private val _isShowHistory = MutableLiveData<Boolean>(false)
    val isShowHistory: LiveData<Boolean>
        get() = _isShowHistory

    fun addToExpression(operand: Int) {
        val expression = expression.value ?: initExpression
        _expression.value = expression + operand
    }

    fun addToExpression(operator: Operator) {
        val expression = expression.value ?: initExpression
        _expression.value = expression + operator
    }

    fun removeLast() {
        val expression = expression.value ?: initExpression
        _expression.value = expression.removeLast()
    }

    fun calculate() {
        val expression = expression.value ?: initExpression
        val result = calculator.calculate(expression.toString())

        if (result == null) {
            _errorEvent.value = Event.CalculatorError
        } else {
            insertHistory(expression, result)
            _expression.value = Expression(listOf(result))
        }
    }

    private fun insertHistory(expression: Expression, result: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertHistory(History(expression.toString(), result))
        }
    }

    private fun getHistoryList() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getHistoryList()
            _historyList.postValue(response)
        }
    }

    fun toggleHistory() {
        if (_isShowHistory.value == false) {
            getHistoryList()
            _isShowHistory.value = true
        } else {
            _isShowHistory.value = false
        }
    }
}
