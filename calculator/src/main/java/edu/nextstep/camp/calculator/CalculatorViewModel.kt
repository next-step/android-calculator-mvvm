package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.nextstep.camp.calculator.domain.*
import kotlinx.coroutines.launch

/**
 * Created by link.js on 2022. 07. 28..
 */
class CalculatorViewModel(
    private val historyRepository: HistoryRepository,
    private val calculator: Calculator = Calculator(),
    initExpression: List<Any> = emptyList(),
) : ViewModel() {
    private var _expression = MutableLiveData(Expression(initExpression))
    val expression: LiveData<Expression>
        get() = _expression

    private var _calculateErrorEvent = MutableLiveData<Event<CalculateError>>()
    val calculateErrorEvent: LiveData<Event<CalculateError>>
        get() = _calculateErrorEvent

    private var _isVisibleHistory = MutableLiveData(false)
    val isVisibleHistory: LiveData<Boolean>
        get() = _isVisibleHistory

    private var _historyList = MutableLiveData<List<History>>()
    val historyList: LiveData<List<History>>
        get() = _historyList

    private fun getExpressionValue() = expression.value ?: Expression.EMPTY

    fun addToExpression(operand: Int) {
        _expression.value = getExpressionValue() + operand
    }

    fun addToExpression(operator: Operator) {
        _expression.value = getExpressionValue() + operator
    }

    fun removeLast() {
        _expression.value = getExpressionValue().removeLast()
    }

    fun calculate() {
        val expression = _expression.value.toString()
        val result = calculator.calculate(expression)
        if (result == null) {
            _calculateErrorEvent.value = Event(CalculateError.ExpressionError)
        } else {
            addHistory(History(expression = expression, result = result))
            _expression.value = Expression(listOf(result))
        }
    }

    fun setVisibilityHistory(isVisible: Boolean) {
        _isVisibleHistory.value = isVisible
    }

    fun loadHistories() {
        viewModelScope.launch {
            _historyList.value = historyRepository.getHistories()
        }
    }

    fun saveHistories() {
        historyList.value?.let {
            viewModelScope.launch {
                historyRepository.setHistories(it)
            }
        }
    }

    private fun addHistory(history: History) {
        _historyList.value = _historyList.value.orEmpty() + history
    }

    sealed class CalculateError {
        object ExpressionError : CalculateError()
    }
}
