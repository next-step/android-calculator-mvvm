package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.domain.Calculator
import edu.nextstep.camp.domain.Expression
import edu.nextstep.camp.domain.Operator

class CalculatorViewModel(private var expression: Expression = Expression.EMPTY) : ViewModel() {
    private val calculator = Calculator()


    private val _result = MutableLiveData(expression)
    val result: LiveData<Expression>
        get() = _result

    private val _error = SingleLiveEvent<CalculatorErrorEvent>()
    val error: LiveData<CalculatorErrorEvent>
        get() = _error

    fun addOperandToExpression(operand: Int) {
        _result.value = _result.value?.plus(operand)
    }

    fun addOperatorToExpression(operator: Operator) {
        _result.value = _result.value?.plus(operator)
    }

    fun deleteExpression() {
        expression = _result.value?.removeLast() ?: Expression.EMPTY
        _result.value = expression
    }

    fun calculateExpression() {
        val result = calculator.calculate(_result.value?.toString() ?: Expression.EMPTY.toString())
        if (result == null) {
            _error.value = CalculatorErrorEvent.IncompleteExpressionError
        } else {
            expression = Expression(listOf(result))
            _result.value = expression
        }
    }
}