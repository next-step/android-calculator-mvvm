package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.ExpressionHistories
import edu.nextstep.camp.calculator.domain.ExpressionHistory
import edu.nextstep.camp.calculator.domain.ExpressionHistoryRepository
import edu.nextstep.camp.calculator.domain.Operator
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val expressionHistoryRepository: ExpressionHistoryRepository =
        CalculatorApp.INSTANCE.expressionHistoryRepository
) : ViewModel() {
    private var expression = Expression.EMPTY
        set(value) {
            field = value
            refreshDisplay()
        }
    private val calculator = Calculator()

    private val _display: MutableLiveData<String> = MutableLiveData()
    val display: LiveData<String> = _display

    private val _isExpressionHistoryOpen: MutableLiveData<Boolean> = MutableLiveData(false)
    val isExpressionHistoryOpen: LiveData<Boolean> = _isExpressionHistoryOpen

    private val _expressionHistories: MutableLiveData<ExpressionHistories> =
        MutableLiveData(ExpressionHistories.EMPTY)
    val expressionHistories: LiveData<ExpressionHistories> = _expressionHistories

    private val _viewEvent: SingleLiveEvent<ViewEvent> = SingleLiveEvent()
    val viewEvent: LiveData<ViewEvent> = _viewEvent

    fun addOperand(operand: Int) {
        expression += operand
    }

    fun addOperator(operator: Operator) {
        expression += operator
    }

    fun removeLast() {
        expression = expression.removeLast()
    }

    fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            _viewEvent.value = ViewEvent.IncompleteExpressionError
            return
        }
        addExpressionHistoryItem(expression, result)
        expression = Expression.EMPTY + result
    }

    fun toggleExpressionHistory() {
        if (isExpressionHistoryOpen.value == true) closeExpressionHistory()
        else openExpressionHistory()
    }

    fun loadExpressionHistories() {
        viewModelScope.launch {
            _expressionHistories.value = ExpressionHistories(expressionHistoryRepository.getAll())
        }
    }

    fun saveExpressionHistories() {
        viewModelScope.launch {
            expressionHistories.value?.histories
                ?.let { expressionHistoryRepository.setAll(it) }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

    private fun addExpressionHistoryItem(expression: Expression, result: Int) {
        val historyItem = ExpressionHistory(expression.toString(), result)
        _expressionHistories.value = _expressionHistories.value?.add(historyItem)
    }

    private fun closeExpressionHistory() {
        _isExpressionHistoryOpen.value = false
    }

    private fun openExpressionHistory() {
        _isExpressionHistoryOpen.value = true
    }

    private fun refreshDisplay() {
        _display.value = expression.toString()
    }

    sealed class ViewEvent {
        object IncompleteExpressionError : ViewEvent()
    }
}