package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator

class CalculatorViewModel(initFormula: List<Any> = emptyList()) : ViewModel() {
    private val calculator = Calculator()
    private var expression = Expression(initFormula)

    private var _formula = MutableLiveData<String>()
    val formula: LiveData<String>
        get() = _formula

    private var _toastEvent = SingleLiveEvent<String>()
    val toastEvent: LiveData<String>
        get() = _toastEvent


    init {
        setFormula()
    }

    fun addToExpression(operand: Int) {
        expression += operand
        setFormula()
    }

    fun addToExpression(operator: Operator) {
        expression += operator
        setFormula()
    }

    fun removeLast() {
        expression = expression.removeLast()
        setFormula()
    }

    fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            _toastEvent.value = "완성되지 않은 수식입니다"
        } else {
            expression = Expression(listOf(result))
            setFormula()
        }
    }

    private fun setFormula() {
        _formula.value = expression.toString()
    }
}
