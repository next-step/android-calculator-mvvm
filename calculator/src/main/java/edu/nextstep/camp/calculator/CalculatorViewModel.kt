package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.event.Event
import edu.nextstep.camp.calculator.event.SingleLiveEvent

class CalculatorViewModel : ViewModel() {

    private val calculator = Calculator()

    private val _expression = MutableLiveData(Expression.EMPTY)
    val expression: LiveData<Expression> = _expression

    private val _showEvent = SingleLiveEvent<Event>()
    val showEvent: LiveData<Event> = _showEvent

    fun addOperator(operator: Operator) {
        _expression.value = currentExpression() + operator
    }

    fun addOperand(operand: Int) {
        _expression.value = currentExpression() + operand
    }

    fun removeLast() {
        _expression.value = currentExpression().removeLast()
    }

    fun calculate() {
        val expression: String = currentExpression().toString()
        val result = calculator.calculate(expression)

        if (result == null) {
            _showEvent.value = Event.Error("완성되지 않은 수식입니다.")
            return
        }

        _expression.value = Expression.EMPTY + result
    }

    private fun currentExpression(): Expression {
        return _expression.value ?: Expression.EMPTY
    }


}