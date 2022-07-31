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
    initExpression: List<Any> = emptyList(),
) : ViewModel() {
    private var _expression = MutableLiveData(Expression(initExpression))
    val expression: LiveData<Expression>
        get() = _expression

    private var _calculateErrorEvent = MutableLiveData<Event<CalculateError>>()
    val calculateErrorEvent: LiveData<Event<CalculateError>>
        get() = _calculateErrorEvent

    private var _isVisibleHistoryLayout = MutableLiveData(false)
    val isVisibleHistoryLayout: LiveData<Boolean>
        get() = _isVisibleHistoryLayout

    private fun getExpressionValue() = expression.value ?: Expression.EMPTY

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
        val result = calculator.calculate(_expression.value.toString())
        if (result == null) {
            _calculateErrorEvent.value = Event(CalculateError.ExpressionError)
        } else {
            _expression.value = Expression(listOf(result))
        }
    }

    fun setVisibilityHistoryLayout(isVisible: Boolean) {
        _isVisibleHistoryLayout.value = isVisible
    }

    sealed class CalculateError {
        object ExpressionError : CalculateError()
    }
}
