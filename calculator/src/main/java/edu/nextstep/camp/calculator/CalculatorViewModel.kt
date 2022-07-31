package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorViewModel(
    expression: Expression = Expression.EMPTY,
    private val calculator: Calculator = Calculator()
) : ViewModel() {

    private val _expression = MutableLiveData(expression)
    val expression: LiveData<Expression>
        get() = _expression

    private val _calculatorError = SingleLiveEvent<Unit>()
    val calculatorError: LiveData<Unit> = _calculatorError

    fun addToExpression(operand: Int) {
        _expression.value?.let {
            _expression.value = it + operand
        }
    }

    fun addToExpression(operator: Operator) {
        _expression.value?.let {
            _expression.value = it + operator
        }
    }

    fun removeLast() {
        _expression.value?.let {
            _expression.value = it.removeLast()
        }
    }

    fun calculate() {
        _expression.value?.let {
            val result = calculator.calculate(it.toString())
            if (result != null) {
                _expression.value = Expression(listOf(result))
            } else {
                _calculatorError.call()
            }
        }
    }
}
