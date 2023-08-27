package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator

class CalculatorViewModel : ViewModel() {
    private val calculator = Calculator()
    private var expression = Expression.EMPTY
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
            expression = Expression(listOf(result))
            _result.value = result.toString()
        }
    }
}

sealed interface UiEffect {
    object InCompleteExpressionError : UiEffect
    data class ShowErrorMessage(val message: String?) : UiEffect
}
