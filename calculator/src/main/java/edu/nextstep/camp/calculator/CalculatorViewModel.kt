package edu.nextstep.camp.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.IncompleteExpressionException
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.domain.model.ExpressionHistory
import edu.nextstep.camp.calculator.domain.repository.ExpressionHistoryRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CalculatorViewModel(
    initialExpression: Expression = Expression.EMPTY,
    private val calculator: Calculator = Calculator(),
    private val expressionHistoryRepository: ExpressionHistoryRepository
) : ViewModel() {

    private val _expression = MutableStateFlow(initialExpression)
    val expression = _expression.asStateFlow()

    private val _errorEvent = MutableSharedFlow<Throwable>()
    val errorEvent = _errorEvent.asSharedFlow()

    private val _expressionHistory = MutableStateFlow<List<ExpressionHistory>?>(null)
    val expressionHistory = _expressionHistory.asStateFlow()

    private val _isHistoryVisible = MutableStateFlow(false)
    val isHistoryVisible = _isHistoryVisible.asStateFlow()

    fun addToExpression(operand: Int) {
        _expression.value = expression.value + operand
    }

    fun addToExpression(operator: Operator) {
        _expression.value = expression.value + operator
    }

    fun removeLast() {
        _expression.value = expression.value.removeLast()
    }

    fun calculate() = viewModelScope.launch {
        val result = calculator.calculate(expression.value.toString())
        if (result == null) {
            _errorEvent.emit(Throwable(IncompleteExpressionException()))
        } else {
            insertExpressionHistory(
                ExpressionHistory(
                    expression = expression.value.toString(),
                    result = result
                )
            )
            _expression.value = Expression(listOf(result))
        }
    }

    private fun insertExpressionHistory(history: ExpressionHistory) = viewModelScope.launch {
        expressionHistoryRepository.insert(history)
            .onSuccess {}
            .onFailure { throwable ->
                _errorEvent.emit(throwable)
            }
    }

    fun getAllExpressionHistoryAndShowHistory() = viewModelScope.launch {
        if (isHistoryVisible.value) {
            _isHistoryVisible.value = false
        } else {
            expressionHistoryRepository.getAll()
                .onSuccess {
                    _expressionHistory.value = it
                    _isHistoryVisible.value = true
                }
                .onFailure { throwable ->
                    _errorEvent.emit(throwable)
                }
        }
    }
}
