package edu.nextstep.camp.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CalculatorViewModel(private val calculator: Calculator = Calculator()) : ViewModel() {

    private val _expression = MutableStateFlow(Expression.EMPTY)
    val expression = _expression.asStateFlow()

    private val _incompleteExpressionErrorEvent = MutableSharedFlow<Unit>()
    val incompleteExpressionErrorEvent = _incompleteExpressionErrorEvent.asSharedFlow()

    fun addToExpression(operand: Int) = viewModelScope.launch {
        _expression.emit(expression.value + operand)
    }

    fun addToExpression(operator: Operator) = viewModelScope.launch {
        _expression.emit(expression.value + operator)
    }

    fun removeLast() = viewModelScope.launch {
        _expression.emit(expression.value.removeLast())
    }

    fun calculate() = viewModelScope.launch {
        val result = calculator.calculate(expression.value.toString())
        if (result == null) {
            _incompleteExpressionErrorEvent.emit(Unit)
        } else {
            _expression.emit(Expression(listOf(result)))
        }
    }

}
