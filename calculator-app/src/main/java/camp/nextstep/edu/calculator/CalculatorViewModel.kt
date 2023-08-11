package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator

class CalculatorViewModel : ViewModel() {

    private val _expression = MutableLiveData(Expression.EMPTY)
    val expression: LiveData<Expression>
        get() = _expression

    private val _event = MutableLiveData<EventType>()
    val event: LiveData<EventType>
        get() = _event

    fun addToExpression(operand: Int) {
        val expressionValue = _expression.value ?: return
        _expression.value = expressionValue + operand
    }

    fun addToExpression(operator: Operator) {
        val expressionValue = _expression.value ?: return
        _expression.value = expressionValue + operator
    }

    fun removeLast() {
        val expressionValue = _expression.value ?: return
        _expression.value = expressionValue.removeLast()
    }

    fun calculate() {
        val expressionValue = _expression.value ?: return

        val result = Calculator().calculate(expressionValue.toString())
        if (result == null) {
            _event.value = EventType.SHOW_TOAST
        } else {
            _expression.value = Expression(listOf(result))
        }
    }

    enum class EventType {
        SHOW_TOAST
    }
}
