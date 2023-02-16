package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator

class CalculatorViewModel : ViewModel() {

    private val _text = MutableLiveData<String>()
    val text: LiveData<String>
        get() = _text

    private val _showToastMessage = MutableLiveData<Boolean>()
    val showToastMessage: LiveData<Boolean>
        get() = _showToastMessage

    private val calculator = Calculator()
    private var expression = Expression.EMPTY

    fun addToExpression(operand: Int) {
        expression += operand
        _text.value = expression.toString()
    }

    fun addToExpression(operator: Operator) {
        expression += operator
        _text.value = expression.toString()
    }

    fun removeLast() {
        expression = expression.removeLast()
        _text.value = expression.toString()
    }

    fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result != null) {
            expression = Expression(listOf(result))
            _text.value = expression.toString()
        } else {
            _showToastMessage.value = true
        }

    }

}