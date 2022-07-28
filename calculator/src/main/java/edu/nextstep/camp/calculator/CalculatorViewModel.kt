package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorViewModel : ViewModel() {
    private val calculator = Calculator()
    private val _expression = MutableLiveData(Expression.EMPTY)
    val expression = _expression as LiveData<Expression>

    private val _sideEffect = MutableLiveData<SideEffect>(SideEffect.None)
    val sideEffect = _sideEffect as LiveData<SideEffect>

    fun addToExpression(operand: Int) {
        _expression.value = getExpressionValue() + operand
    }

    fun addToExpression(operator: Operator) {
        _expression.value = getExpressionValue() + operator
    }

    fun removeLast() {
        _expression.value = getExpressionValue().removeLast()
    }

    fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            _sideEffect.value = SideEffect.IncompleteExpressionError
        } else {
            _expression.value = Expression(listOf(result))
        }
    }

    private fun getExpressionValue() = _expression.value ?: Expression.EMPTY

    sealed class SideEffect {
        object IncompleteExpressionError : SideEffect()
        object None : SideEffect()
    }
}
