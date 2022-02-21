package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorViewModel(
    private val calculator: Calculator,
    initialExpression: Expression = Expression.EMPTY
): ViewModel() {
    private val _expression:MutableLiveData<Expression> = MutableLiveData(initialExpression)
    val expression: LiveData<Expression>
        get() = _expression

    private val _calculateFailed:MutableLiveData<Unit> = MutableLiveData()
    val calculateFailed: LiveData<Unit>
        get() = _calculateFailed

    fun addToExpression(operand: Int) {
        val expression = _expression.value ?: Expression.EMPTY
        _expression.value = expression.plus(operand)
    }

    fun addToExpression(operator: Operator) {
        val expression = _expression.value ?: Expression.EMPTY
        _expression.value = expression.plus(operator)
    }

    fun calculate() {
        val expression: Expression = _expression.value ?: Expression.EMPTY
        val calculateValue: Int = calculator.calculate(expression.toString())
            ?: kotlin.run {
                _calculateFailed.value = Unit
                return
            }
        _expression.value = Expression(listOf(calculateValue))
    }

    fun removeLast() {
        val expression = _expression.value ?: Expression.EMPTY
        _expression.value = expression.removeLast()
    }
}