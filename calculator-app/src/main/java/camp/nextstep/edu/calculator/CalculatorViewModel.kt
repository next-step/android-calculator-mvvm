package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator

class CalculatorViewModel : ViewModel() {
    private var _expression = MutableLiveData(Expression.EMPTY)
    val expression: LiveData<Expression>
        get() = _expression

    private var _result = MutableLiveData<Int>()
    val result: LiveData<Int>
        get() = _result

    private var _isCalculatePossible = SingleLiveEvent<Unit>()
    val isCalculatePossible
        get() = _isCalculatePossible

    private val calculator = Calculator()

    fun addToExpression(operand: Int) {
        _expression.value = _expression.value?.plus(operand)
    }

    fun addToExpression(operator: Operator) {
        _expression.value = _expression.value?.plus(operator)
    }

    fun removeLast() {
        _expression.value = _expression.value?.removeLast()
    }

    fun calculate() {
        val calculateResult = calculator.calculate(_expression.value.toString())

        if (calculateResult == null) {
            _isCalculatePossible.value = Unit
        } else {
            _result.value = calculateResult!!
        }
    }
}