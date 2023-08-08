package camp.nextstep.edu.calculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.calculator.SingleLiveEvent
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator

class CalculatorViewModel : ViewModel() {
    private val calculator = Calculator()

    private val _expression = MutableLiveData(Expression.EMPTY)
    val expression: LiveData<Expression> = _expression

    private val _uiState = SingleLiveEvent<CalculatorUiState>()
    val uiState: LiveData<CalculatorUiState> = _uiState

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

    fun calculate() {
        val expression = _expression.value ?: return
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            _uiState.value = CalculatorUiState.ErrorNotCompleteExpression()
        } else {
            _expression.value = Expression(listOf(result))
        }
    }
}

sealed class CalculatorUiState {
    data class ErrorNotCompleteExpression(val exception: String = "") : CalculatorUiState()
}
