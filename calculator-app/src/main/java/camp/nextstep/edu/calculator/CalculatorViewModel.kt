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
    private val _uiState = MutableLiveData<UiState>().apply { value = UiState.Result(result = "") }
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

    fun calculate() = viewModelScope.launch(Dispatchers.IO) {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            _uiEffect.value = UiEffect.InCompleteExpressionError
        } else {
            repository.saveMemory(expression.toString(), result)
            expression = Expression(listOf(result))
            _uiState.value = UiState.Result(result = expression.toString())
        }
    }

    fun onClickHistory() {
        if (uiState.value is UiState.Result) {
            loadHistory()
        } else {
            _uiState.value = UiState.Result(result = expression.toString())
        }
    }

    private fun loadHistory() = viewModelScope.launch {
        val result = repository.findMemories()

        _uiState.value = UiState.History(
            history = result
        )
    }
}

sealed interface UiState {
    data class Result(
        val result: String
    ) : UiState

    data class History(
        val history: List<Memory>
    ) : UiState
}


sealed interface UiEffect {
    object InCompleteExpressionError : UiEffect
    data class ShowErrorMessage(val message: String?) : UiEffect
}
