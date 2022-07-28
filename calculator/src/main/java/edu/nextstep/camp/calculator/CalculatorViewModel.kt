package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.ExpressionHistoryItem
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorViewModel : ViewModel() {
    private var expression = Expression.EMPTY
        set(value) {
            field = value
            refreshDisplay()
        }
    private val calculator = Calculator()

    private val _display: MutableLiveData<String> = MutableLiveData()
    val display: LiveData<String> = _display

    private val _hasExpressionHistoryOpen: MutableLiveData<Boolean> = MutableLiveData(false)
    val hasExpressionHistoryOpen: LiveData<Boolean> = _hasExpressionHistoryOpen

    private val _expressionHistories: MutableLiveData<List<ExpressionHistoryItem>> =
        MutableLiveData(emptyList())
    val expressionHistories: LiveData<List<ExpressionHistoryItem>> = _expressionHistories

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
        if (hasExpressionHistoryOpen.value == true) closeExpressionHistory()
        else openExpressionHistory()
    }

    private fun addExpressionHistoryItem(expression: Expression, result: Int) {
        val historyItem = ExpressionHistoryItem(expression.toString(), result)
        _expressionHistories.value = _expressionHistories.value.orEmpty() + historyItem
    }

    private fun closeExpressionHistory() {
        _hasExpressionHistoryOpen.value = false
    }

    private fun openExpressionHistory() {
        _hasExpressionHistoryOpen.value = true
    }

    private fun refreshDisplay() {
        _display.value = expression.toString()
    }

    sealed class ViewEvent {
        object IncompleteExpressionError : ViewEvent()
    }
}