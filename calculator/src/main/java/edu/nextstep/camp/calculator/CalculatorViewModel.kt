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

    private val _sideEffect : MutableLiveData<Event<SideEffect>> = MutableLiveData(Event(SideEffect.None))
    val sideEffect = _sideEffect as LiveData<Event<SideEffect>>

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
        runCatching { calculator.calculate(getExpressionValue().toString()) }
            .onSuccess {
                if (it == null) {
                    _sideEffect.value = Event(SideEffect.IncompleteExpressionError)
                } else {
                    _expression.value = Expression(listOf(it))
                }
            }
            .onFailure {
                if (it is ArithmeticException) {
                    _sideEffect.value = Event(SideEffect.DivideByZeroError)
                } else {
                    _sideEffect.value = Event(SideEffect.UnknownError)
                }
            }
    }

    private fun getExpressionValue(): Expression = _expression.value ?: Expression.EMPTY

    sealed class SideEffect {
        object IncompleteExpressionError : SideEffect()
        object UnknownError : SideEffect()
        object DivideByZeroError : SideEffect()
        object None : SideEffect()
    }
}
