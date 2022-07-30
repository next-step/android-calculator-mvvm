package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

/**
 * Created by link.js on 2022. 07. 28..
 */
class CalculatorViewModel(
    private val calculator: Calculator = Calculator(),
    initExpression: Expression = Expression.EMPTY,
) : ViewModel() {
    private var _expression = MutableLiveData(initExpression)
    val expression: LiveData<Expression>
        get() = _expression

    private var _incompleteExpressionErrorEvent = MutableLiveData(ExpressionError.NONE)
    val incompleteExpressionErrorEvent: LiveData<ExpressionError>
        get() = _incompleteExpressionErrorEvent

    fun addToExpression(operand: Int) {
        _expression.value = _expression.value?.plus(operand)
    }

    fun addToExpression(operator: Operator) {
        _expression.value = _expression.value?.plus(operator)
    }

    fun removeLast() {
        _expression.value = _expression.value?.removeLast()
    }

    fun calculate() {
        val result = calculator.calculate(_expression.value.toString())
        if (result == null) {
            _incompleteExpressionErrorEvent.value = ExpressionError.ERROR
        } else {
            _expression.value = Expression(listOf(result))
        }
    }

    enum class ExpressionError {
        NONE, ERROR
    }
}
