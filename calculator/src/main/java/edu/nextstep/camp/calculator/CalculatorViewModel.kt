package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.nextstep.camp.calculator.data.CalculatorDatabase
import edu.nextstep.camp.calculator.data.model.CalculateResultEntity
import edu.nextstep.camp.calculator.data.toCalculateResult
import edu.nextstep.camp.calculator.domain.CalculateHistory
import edu.nextstep.camp.calculator.domain.CalculateResult
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private var expression: Expression = Expression.EMPTY,
    private val calculatorDatabase: CalculatorDatabase,
    private val calculator: Calculator = Calculator(),
) : ViewModel() {

    private val _calculateHistory = MutableLiveData<CalculateHistory>()
    val calculateHistory: LiveData<CalculateHistory>
        get() = _calculateHistory

    private val _calculatorText = MutableLiveData("")
    val calculatorText: LiveData<String>
        get() = _calculatorText

    private val _isShowCalculatorHistory = MutableLiveData(false)
    val isShowingCalculatorHistory: LiveData<Boolean>
        get() = _isShowCalculatorHistory

    private val _showIncompleteExpressionError = MutableLiveData<Unit>()
    val showIncompleteExpressionError: LiveData<Unit>
        get() = _showIncompleteExpressionError

    fun addToExpression(operand: Int) {
        expression += operand
        updateCalculatorText(expression.toString())
    }

    fun addToExpression(operator: Operator) {
        expression += operator
        updateCalculatorText(expression.toString())
    }

    fun removeLast() {
        expression = expression.removeLast()
        updateCalculatorText(expression.toString())
    }

    fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            _showIncompleteExpressionError.value = Unit
        } else {
            putCalculateHistory(expression, result)
            expression = Expression(listOf(result))
            updateCalculatorText(expression.toString())
        }
    }

    private fun updateCalculatorText(text: String) {
        _calculatorText.value = text
    }

    fun toggleCalculatorHistoryShowing() {
        _isShowCalculatorHistory.value = _isShowCalculatorHistory.value?.not()
    }

    private fun putCalculateHistory(expression: Expression, result: Int) {
        viewModelScope.launch {
            calculatorDatabase.calculateResultDao().insertCalculateResult(CalculateResultEntity(
                expression = expression.toString(),
                result = result,
            ))
        }
    }

    fun getCalculateHistories() {
        viewModelScope.launch {
            calculatorDatabase.calculateResultDao().getCalculateResults()
                .map(::mapToCalculateHistory)
                .collect {
                    _calculateHistory.value = CalculateHistory().apply {
                        putCalculateResults(it)
                    }
                }
        }
    }

    private fun mapToCalculateHistory(
        calculateHistoryEntities: List<CalculateResultEntity>?,
    ): List<CalculateResult> {
        return calculateHistoryEntities?.map { calculateResultEntity ->
            calculateResultEntity.toCalculateResult()
        } ?: listOf()
    }
}
