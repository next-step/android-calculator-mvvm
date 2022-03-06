package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.nextstep.camp.calculator.data.History
import edu.nextstep.camp.calculator.data.HistoryDao
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val calculator: Calculator = Calculator(),
    expression: Expression = Expression.EMPTY,
    private val historyDao: HistoryDao
) : ViewModel() {

    private val _expression = MutableLiveData<Expression>(expression)
    val expression: LiveData<Expression>
        get() = _expression

    private val _expressionError = MutableLiveData<Event<Unit>>()
    val expressionError: LiveData<Event<Unit>>
        get() = _expressionError

    private val _historyVisible = MutableLiveData<Boolean>(false)
    val historyVisible: LiveData<Boolean>
        get() = _historyVisible

    private val _expressionHistory = MutableLiveData<List<History>>(emptyList())
    val expressionHistory: LiveData<List<History>>
        get() = _expressionHistory

    init {
        viewModelScope.launch {
            _expressionHistory.postValue(historyDao.getAll())
        }
    }

    fun addToExpression(operand: Int) {
        _expression.value = _expression.value?.plus(operand) ?: Expression.EMPTY + operand
    }

    fun addToExpression(operator: Operator) {
        _expression.value = _expression.value?.plus(operator)
    }

    fun removeLast() {
        _expression.value = expression.value?.removeLast()
    }

    fun calculate() {
        val expression = expression.value.toString()
        val result = calculator.calculate(expression)
        if (result == null) {
            _expressionError.value = Event(Unit)
            return
        }
        _expression.value = Expression.EMPTY + result

        val newHistory = History(expression, result.toString())
        viewModelScope.launch {
            historyDao.insert(newHistory)
            _expressionHistory.value = historyDao.getAll()
        }

    }

    fun showAndHideHistoryList() {
        val isVisible = historyVisible.value ?: false
        _historyVisible.value = !isVisible
    }
}