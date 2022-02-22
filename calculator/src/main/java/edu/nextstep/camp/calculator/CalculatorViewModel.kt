package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.utils.Event
import edu.nextstep.camp.calculator.utils.NonNullLiveData
import edu.nextstep.camp.domain.calculator.Calculator
import edu.nextstep.camp.domain.calculator.CalculatorMemory
import edu.nextstep.camp.domain.calculator.Expression
import edu.nextstep.camp.domain.calculator.Operator

class CalculatorViewModel(
    initialExpression: Expression = Expression.EMPTY,
    private val initialCalculatorMemory: CalculatorMemory = CalculatorMemory()
) : ViewModel() {

    private val _expression = NonNullLiveData(initialExpression)
    val expression: LiveData<Expression>
        get() = _expression

    private val _calculatorMemory = MutableLiveData<List<CalculatorRecordUiState>>()
    val calculatorMemory: LiveData<List<CalculatorRecordUiState>>
        get() = _calculatorMemory

    private val _incompleteExpressionEvent = MutableLiveData<Event<Boolean>>()
    val incompleteExpressionEvent: LiveData<Event<Boolean>>
        get() = _incompleteExpressionEvent

    private val _isVisibleCalculatorMemory = NonNullLiveData(false)
    val isVisibleCalculatorMemory: LiveData<Boolean>
        get() = _isVisibleCalculatorMemory

    init {
        _calculatorMemory.value = initialCalculatorMemory.records.map {
            CalculatorRecordUiState(it.expression, it.result)
        }
    }

    fun addToExpression(operand: Int) {
        _expression.value += operand
    }

    fun addToExpression(operator: Operator) {
        _expression.value += operator
    }

    fun removeLast() {
        _expression.value = _expression.value.removeLast()
    }

    fun calculate() {
        val currentExpression = _expression.value
        val result = Calculator.calculate(currentExpression.toString())
        if (result == null) {
            _incompleteExpressionEvent.value = Event(true)
            return
        }
        initialCalculatorMemory.addRecord(_expression.value, result)
        _calculatorMemory.value = initialCalculatorMemory.records.map {
            CalculatorRecordUiState(it.expression, it.result)
        }
        _expression.value = Expression(result)
    }

    fun toggleVisibilityOfCalculatorMemory() {
        val isVisible = _isVisibleCalculatorMemory.value
        _isVisibleCalculatorMemory.value = !isVisible
    }
}
