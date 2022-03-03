package edu.nextstep.camp.calculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.nextstep.camp.calculator.CalculatorViewType
import edu.nextstep.camp.calculator.ExpressionView
import edu.nextstep.camp.calculator.SingleLiveEvent
import edu.nextstep.camp.domain.*
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val calculator: Calculator = Calculator(),
    private val repository: CalculatorRepository
) : ViewModel() {

    private val _expressionEvent = SingleLiveEvent<Expression>()
    val expressionEvent: LiveData<Expression> get() = _expressionEvent

    private val _errorEvent = SingleLiveEvent<Unit>()
    val errorEvent: LiveData<Unit> get() = _errorEvent

    private val _viewTypeEvent = SingleLiveEvent<CalculatorViewType>()
    val viewTypeEvent: LiveData<CalculatorViewType> get() = _viewTypeEvent

    private val _memoriesEvent = SingleLiveEvent<List<Calculation>?>()
    val memoriesEvent: LiveData<List<Calculation>?> get() = _memoriesEvent

    private val currentExpression: Expression get() = _expressionEvent.value ?: Expression.EMPTY
    private val viewType: CalculatorViewType get() = _viewTypeEvent.value ?: ExpressionView

    fun addToExpression(operand: Int) {
        val newExpression = currentExpression + operand
        _expressionEvent.value = newExpression
    }

    fun addToExpression(operator: Operator) {
        val newExpression = currentExpression + operator
        _expressionEvent.value = newExpression
    }

    fun removeLast() {
        val newExpression = currentExpression.removeLast()
        _expressionEvent.value = newExpression
    }

    fun calculate() {
        val result = calculator.calculate(currentExpression.toString())
        if (result == null) {
            _errorEvent.call()
            return
        }

        saveExpression(currentExpression, result)
        _expressionEvent.value = Expression(listOf(result))
    }

    fun toggleViewType() {
        _viewTypeEvent.postValue(viewType.toggle())
        getMemories()
    }

    private fun getMemories() = viewModelScope.launch {
        val memories = if (viewType is ExpressionView) repository.getAll() else null
        _memoriesEvent.postValue(memories)

    }

    private fun saveExpression(expression: Expression, result: Int) = viewModelScope.launch {
        val memory = Calculation(expression.toString(), result.toString())
        repository.insert(memory)
    }
}