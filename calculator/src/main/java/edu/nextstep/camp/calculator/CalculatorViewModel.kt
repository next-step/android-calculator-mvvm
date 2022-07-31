package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.nextstep.camp.calculator.data.Injector
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import kotlinx.coroutines.launch

class CalculatorViewModel(
    expression: Expression = Expression.EMPTY,
    private val calculator: Calculator = Calculator(Injector.historyRepository),
) : ViewModel() {

    private val _expression = MutableLiveData(expression)
    val expression: LiveData<Expression>
        get() = _expression

    private val _calculatorError = SingleLiveEvent<Unit>()
    val calculatorError: LiveData<Unit>
        get() = _calculatorError

    private val _isShowingHistory = MutableLiveData(false)
    val isShowingHistory: LiveData<Boolean>
        get() = _isShowingHistory

    private val _history = MutableLiveData<List<HistoryItem>>()
    val history: LiveData<List<HistoryItem>>
        get() = _history

    init {
        loadHistory()
    }

    private fun loadHistory() {
        viewModelScope.launch {
            calculator.loadHistory()
        }
    }

    fun addToExpression(operand: Int) {
        _expression.value = _expression.value?.plus(operand)
    }

    fun addToExpression(operator: Operator) {
        _expression.value = _expression.value?.plus(operator)
    }

    fun removeLast() {
        _expression.value = _expression.value?.removeLast()
    }

    fun calculate() {
        val expression = _expression.value ?: return

        val result = calculator.calculate(expression.toString())
        if (result != null) {
            _expression.value = Expression(listOf(result))
        } else {
            _calculatorError.call()
        }
    }

    fun toggleHistory() {
        val isShowingHistory = _isShowingHistory.value ?: return

        if (!isShowingHistory) {
            _history.value = calculator.historyList.map { history -> history.toItem() }
            saveHistory()
        }

        _isShowingHistory.value = !isShowingHistory
    }

    private fun saveHistory() {
        viewModelScope.launch {
            calculator.saveHistory()
        }
    }
}
