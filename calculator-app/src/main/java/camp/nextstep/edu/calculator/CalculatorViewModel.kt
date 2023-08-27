package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator
import camp.nextstep.edu.calculator.domain.repository.CalculatorRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class CalculatorViewModel(
    private val repository: CalculatorRepository,
    private var expression: Expression = Expression.EMPTY
) : ViewModel() {

    private val calculator = Calculator()
    private val _result = MutableLiveData<String>().apply { value = "" }
    val result: LiveData<String>
        get() = _result

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
            .onSuccess { _result.value = expression.toString() }
            .onFailure { _uiEffect.value = UiEffect.ShowErrorMessage(it.message) }
    }

    fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            _uiEffect.value = UiEffect.InCompleteExpressionError
        } else {
            viewModelScope.launch {
                repository.saveMemory(expression.toString(), result)
            }
            expression = Expression(listOf(result))
            _result.value = result.toString()
        }
    }

    fun loadHistory() = viewModelScope.launch {
        val history = repository.findMemories()
        _uiEffect.value = UiEffect.ShowErrorMessage(history.toString())
    }
}

class CalculatorViewModelFactory(private val repository: CalculatorRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalculatorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CalculatorViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

sealed interface UiEffect {
    object InCompleteExpressionError : UiEffect
    data class ShowErrorMessage(val message: String?) : UiEffect
}
