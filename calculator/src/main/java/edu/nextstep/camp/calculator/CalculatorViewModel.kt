package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.*

class CalculatorViewModel(private val calculator: Calculator) : ViewModel() {

    private val _displayResult = MutableLiveData<String>()
    val displayResult: LiveData<String> get() = _displayResult

    private val _showIncompleteExpressionError = SingleLiveEvent<Unit>()
    val showIncompleteExpressionError: LiveData<Unit> get() = _showIncompleteExpressionError

    private val _isMemoryMode = MutableLiveData(false)
    val isMemoryMode: LiveData<Boolean> get() = _isMemoryMode

    private val _memoryResult = MutableLiveData(Memory.EMPTY)
    val memoryResult: LiveData<Memory> get() = _memoryResult

    private var expression = Expression.EMPTY
    private var memory = Memory.EMPTY

    fun addToExpression(operand: Int) {
        expression += operand
        updateDisplayResultByExpression()
    }

    fun addToExpression(operator: Operator) {
        expression += operator
        updateDisplayResultByExpression()
    }

    fun removeLast() {
        expression = expression.removeLast()
        updateDisplayResultByExpression()
    }

    fun calculate() {
        val result = calculator.calculate(expression.toString()) ?: run {
            _showIncompleteExpressionError.value = Unit
            return
        }

        memory += Memory.Item(expression.toString(), result)
        expression = Expression(listOf(result))

        updateDisplayResultByExpression()
    }

    private fun updateDisplayResultByExpression() {
        toggleExpressionMode()
        _displayResult.value = expression.toString()
    }

    fun toggleMode() {
        when (isMemoryMode.value) {
            true -> toggleExpressionMode()
            false -> toggleMemoryMode()
        }
    }

    private fun toggleExpressionMode() {
        _isMemoryMode.value = false
    }

    private fun toggleMemoryMode() {
        _isMemoryMode.value = true
        _memoryResult.value = memory
    }
}