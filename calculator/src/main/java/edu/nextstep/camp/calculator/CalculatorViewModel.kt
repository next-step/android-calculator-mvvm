package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.domain.Calculator
import edu.nextstep.camp.domain.Expression
import edu.nextstep.camp.domain.Operator

class CalculatorViewModel(private val calculator: Calculator = Calculator()) : ViewModel() {

    private val _expressionEvent = SingleLiveEvent<Expression>()
    val expressionEvent: LiveData<Expression> get() = _expressionEvent

    private val _errorEvent = SingleLiveEvent<Unit>()
    val errorEvent: LiveData<Unit> get() = _errorEvent

    private val _viewTypeEvent = SingleLiveEvent<CalculatorViewType>()
    val viewTypeEvent: LiveData<CalculatorViewType> get() = _viewTypeEvent

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

        _expressionEvent.value = Expression(listOf(result))
    }

    fun toggleViewType() {
        _viewTypeEvent.value = viewType.toggle()
    }
}