package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Memory
import camp.nextstep.edu.calculator.domain.Operator
import camp.nextstep.edu.calculator.domain.repository.CalculatorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val repository: CalculatorRepository,
    private var expression: Expression = Expression.EMPTY
) : ViewModel() {

    private val calculator = Calculator()
    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState>
        get() = _uiState

    private val _uiEffect = SingleLiveEvent<UiEffect>()
    val uiEffect: LiveData<UiEffect>
        get() = _uiEffect

    fun addToExpression(operand: Int) {
        updateExpression { expression += operand }
    }

    fun addToExpression(operator: Operator) {
        updateExpression { expression += operator }
    }

    fun removeLast() {
        updateExpression { expression = expression.removeLast() }
    }

    private fun updateExpression(action: () -> Unit) {
        runCatching { action() }
            .onSuccess { _uiState.value = UiState.Result(result = expression.toString()) }
            .onFailure { _uiEffect.value = UiEffect.ShowErrorMessage(it.message) }
    }

    fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            _uiEffect.value = UiEffect.InCompleteExpressionError
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                repository.saveMemory(expression.toString(), result)
            }
            expression = Expression(listOf(result))
            _uiState.value = UiState.Result(result = expression.toString())
        }
    }

    fun onClickHistory() {
        if ((uiState.value as? UiState.Result)?.historyMode == false) {
            setHistoryMode(true)
            loadHistory()
        } else {
            setHistoryMode(false)
        }
    }

    fun loadHistory() = viewModelScope.launch {
        val result = repository.findMemories()

        _uiState.value = UiState.History(
            history = result
        )
    }

    private fun setHistoryMode(boolean: Boolean) {
        _uiState.value = UiState.Result(
            result = expression.toString(),
            historyMode = boolean
        )
    }
}

sealed interface UiState {
    data class Result(
        val result: String,
        val historyMode: Boolean = false
    ) : UiState

    data class History(
        val history: List<Memory>,
    ) : UiState
}


sealed interface UiEffect {
    object InCompleteExpressionError : UiEffect
    data class ShowErrorMessage(val message: String?) : UiEffect
}
