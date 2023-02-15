package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator

class CalculatorViewModel : ViewModel() {
    private val _calcText = MutableLiveData<String>("")
    val calcText: LiveData<String> get() = _calcText

    private val _calculatorErrorMessage = SingleLiveEvent<String>()
    val calculatorErrorMessage: LiveData<String> get() = _calculatorErrorMessage

    private var expression: Expression = Expression.EMPTY

    private val calculator = Calculator()

    fun addToExpression(operand: Int) {
        val newExpression = expression + operand
        expression = newExpression
        _calcText.value = expression.toString()
    }

    fun addToExpression(`operator`: Operator) {
        val newExpression = expression + `operator`
        expression = newExpression
        _calcText.value = expression.toString()

    }

    fun removeLast() {
        val newExpression = expression.removeLast()
        expression = newExpression
        _calcText.value = expression.toString()
    }

    fun calculate() {

        val newExpression = calculator.calculate(expression.toString())

        if (newExpression == null) {
            _calculatorErrorMessage.value = "완성되지 않은 수식입니다"
            return
        }

        _calcText.value = (Expression.EMPTY + newExpression).toString()
    }
}
