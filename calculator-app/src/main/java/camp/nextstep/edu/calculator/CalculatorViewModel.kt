package camp.nextstep.edu.calculator

import androidx.annotation.IdRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator

class CalculatorViewModel : ViewModel() {
    private val _calcText = MutableLiveData<String>("")
    val calcText: LiveData<String> get() = _calcText

    @IdRes
    private val _calculatorErrorMessage = SingleLiveEvent<Int>()
    val calculatorErrorMessage: LiveData<Int> get() = _calculatorErrorMessage

    private var expression: Expression = Expression.EMPTY

    private val calculator = Calculator()

    fun addToExpression(operand: Int) {
        expression += operand
        _calcText.value = expression.toString()
    }

    fun addToExpression(`operator`: Operator) {
        expression += `operator`
        _calcText.value = expression.toString()

    }

    fun removeLast() {
        expression = expression.removeLast()
        _calcText.value = expression.toString()
    }

    fun calculate() {

        val newExpression = calculator.calculate(expression.toString())

        if (newExpression == null) {
            _calculatorErrorMessage.value = R.string.incomplete_expression
            return
        }

        expression = Expression.EMPTY + newExpression
        _calcText.value = expression.toString()
    }
}
