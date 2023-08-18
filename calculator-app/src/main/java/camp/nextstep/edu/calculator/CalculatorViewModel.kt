package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator
import kotlin.coroutines.coroutineContext

class CalculatorViewModel(initFormula: List<Any> = emptyList()) : ViewModel() {
    private val calculator = Calculator()

    private var _formula = MutableLiveData(Expression(initFormula))
    val formula: LiveData<Expression>
        get() = _formula

    private var _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int>
        get() = _toastEvent

    fun addToExpression(operand: Int) {
        _formula.value = _formula.value?.plus(operand)
    }

    fun addToExpression(operator: Operator) {
        _formula.value = _formula.value?.plus(operator)
    }

    fun removeLast() {
        _formula.value = _formula.value?.removeLast()
    }

    fun calculate() {
        val result = calculator.calculate(_formula.value.toString())
        if (result == null) {
            _toastEvent.value = R.string.incomplete_expression
        } else {
            _formula.value = Expression(listOf(result))
        }
    }
}
