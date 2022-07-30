package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

/**
 * CalculatorActivity에 대한 viewModel
 * Created by jeongjinhong on 2022. 07. 30..
 */
class CalculatorViewModel : ViewModel() {

    private val calculator = Calculator()

    private var _expression = MutableLiveData(Expression.EMPTY)
    val expression: LiveData<Expression>
        get() = _expression

    private var _errorEvent = MutableLiveData(Event(ErrorEvent.NORMAL))
    val errorEvent: LiveData<Event<ErrorEvent>>
        get() = _errorEvent

    fun addToExpression(operand: Int) {
        _expression.value = (_expression.value ?: Expression.EMPTY) + operand
    }

    fun addToExpression(operator: Operator) {
        _expression.value = (_expression.value ?: Expression.EMPTY) + operator
    }

    fun removeLast() {
        _expression.value = _expression.value?.removeLast() ?: Expression.EMPTY
    }


    fun calculate() {
        val result = calculator.calculate(expression.value.toString())
        if (result == null) {
            _errorEvent.value = Event(ErrorEvent.INCOMPLETE_EXPRESSION_ERROR)
        } else {
            _expression.value = Expression(listOf(result))
        }
    }

    enum class ErrorEvent {
        NORMAL, INCOMPLETE_EXPRESSION_ERROR
    }

}