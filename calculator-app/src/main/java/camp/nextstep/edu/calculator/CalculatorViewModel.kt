package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator

class CalculatorViewModel : ViewModel() {

    private val calculator = Calculator()

    private val _expression = MutableLiveData(Expression.EMPTY)
    val expression: LiveData<Expression> = _expression

    private val _inCompleteExpressionEvent = SingleLiveEvent<Unit>()
    val inCompleteExpressionEvent: LiveData<Unit> = _inCompleteExpressionEvent

    fun addToExpression(operand: Int) {
        _expression.value = _expression.value?.plus(operand)
    }

    fun addToExpression(operator: Operator) {
        _expression.value = _expression.value?.plus(operator)
    }

    fun removeLast() {
        _expression.value = expression.value?.removeLast()
    }

    fun calculate() {
        val result = calculator.calculate(expression.value.toString())
        if (result == null) {
            _inCompleteExpressionEvent.call()
        } else {
            _expression.value = Expression(listOf(result))
        }
    }
}