package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.event.Event

class CalculatorViewModel : ViewModel() {

    private val calculator = Calculator()

    private val _expression: MutableLiveData<Expression> = MutableLiveData()
    val expression: LiveData<Expression> = _expression

    private val _showEvent: MutableLiveData<Event> = MutableLiveData()
    val showEvent: LiveData<Event> = _showEvent

    init {
        _expression.value = Expression.EMPTY
    }

    fun addOperator(operator: Operator) {
        val newExpression = currentExpression() + operator
        _expression.value = newExpression
    }

    fun addOperand(operand: Int) {
        val newExpression = currentExpression() + operand
        _expression.value = newExpression
    }

    fun removeLast() {
        val newExpression = currentExpression().removeLast()
        _expression.value = newExpression
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

    fun currentExpression(): Expression {
        return _expression.value ?: Expression.EMPTY
    }


}