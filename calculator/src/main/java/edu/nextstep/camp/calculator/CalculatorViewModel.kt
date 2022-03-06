package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.nextstep.camp.calculator.utils.Event
import edu.nextstep.camp.calculator.utils.NonNullLiveData
import edu.nextstep.camp.domain.calculator.Calculator
import edu.nextstep.camp.domain.calculator.CalculatorRepository
import edu.nextstep.camp.domain.calculator.Expression
import edu.nextstep.camp.domain.calculator.Operator
import edu.nextstep.camp.domain.calculator.model.CalculatorRecord
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CalculatorViewModel(
    initialExpression: Expression = Expression.EMPTY,
    private val repository: CalculatorRepository
) : ViewModel() {

    private val _expression = NonNullLiveData(initialExpression)
    val expression: LiveData<Expression>
        get() = _expression

    private val _incompleteExpressionEvent = MutableLiveData<Event<Boolean>>()
    val incompleteExpressionEvent: LiveData<Event<Boolean>>
        get() = _incompleteExpressionEvent

    private val _calculatorMemoryVisibility = NonNullLiveData(false)
    val calculatorMemoryVisibility: LiveData<Boolean>
        get() = _calculatorMemoryVisibility

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
        addCalculatorRecord(CalculatorRecord(currentExpression.toString(), result.toString()))
        _expression.value = Expression(result)
    }

    private fun addCalculatorRecord(record: CalculatorRecord) = viewModelScope.launch {
        repository.addRecord(record)
    }

    fun getAllCalculatorRecord(): Flow<List<CalculatorRecord>> = repository.getAllRecord()

    fun toggleCalculatorMemory() {
        _calculatorMemoryVisibility.value = !_calculatorMemoryVisibility.value
    }
}
