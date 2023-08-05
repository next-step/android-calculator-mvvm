package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator

class CalculatorViewModel: ViewModel() {

    private val calculator = Calculator()
    private var expression: Expression = Expression.EMPTY

    private val _text: MutableLiveData<String> = MutableLiveData()
    val text: LiveData<String> = _text

    private val _inCompleteExpressionError: MutableLiveData<Event<Unit>> = MutableLiveData()
    val inCompleteExpressionError: LiveData<Event<Unit>> = _inCompleteExpressionError

    fun addToExpression(operand: Int) {
        expression += operand
        setText(expression)
    }

    fun addToExpression(operator: Operator) {
        expression += operator
        setText(expression)
    }

    fun removeLast() {
        expression = expression.removeLast()
        setText(expression)
    }

    fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            _inCompleteExpressionError.value = Event(Unit)
        } else {
            expression = Expression(listOf(result))
            setText(expression)
        }
    }

    fun removeMemory() {
        expression = Expression.EMPTY
        setText(expression)
    }

    private fun setText(expression: Expression) {
        _text.postValue(expression.toString())
    }
}