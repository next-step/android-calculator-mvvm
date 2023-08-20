package camp.nextstep.edu.calculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import camp.nextstep.edu.calculator.SingleLiveEvent
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator
import camp.nextstep.edu.calculator.domain.repo.History
import camp.nextstep.edu.calculator.domain.repo.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    expression: Expression = Expression.EMPTY,
    private val repository: HistoryRepository
) : ViewModel() {

    private val calculator = Calculator()

    private val _expression = MutableLiveData(expression)
    val expression: LiveData<Expression> = _expression

    private val _uiState = SingleLiveEvent<CalculatorUiState>()
    val uiState: LiveData<CalculatorUiState> = _uiState

    private val _histories = MutableLiveData<List<History>>()
    val histories: LiveData<List<History>> = _histories

    init {
        if (expression != null) {
            _expression.value = expression
        }
    }

    fun addToExpression(operand: Int) {
        val expression = _expression.value ?: return
        _expression.value = expression + operand
    }

    fun addToExpression(operator: Operator) {
        val expression = _expression.value ?: return
        _expression.value = expression + operator
    }

    fun removeLast() {
        _expression.value = _expression.value?.removeLast()
    }

    fun loadHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            val history = repository.loadHistories()

            viewModelScope.launch(Dispatchers.Main) {
                _histories.value = history
            }
        }
    }

    fun calculate() {
        val expression = _expression.value ?: return
        val result = calculator.calculate(expression.toString())

        if (result == null) {
            _uiState.value = CalculatorUiState.ErrorNotCompleteExpression()
        } else {
            val history = History(expression.toString(), result)
            viewModelScope.launch(Dispatchers.IO) {
                repository.addHistory(history)
            }
            _expression.value = Expression(listOf(result))
        }
    }
}

sealed class CalculatorUiState {
    data class ErrorNotCompleteExpression(val exception: String = "") : CalculatorUiState()
}
