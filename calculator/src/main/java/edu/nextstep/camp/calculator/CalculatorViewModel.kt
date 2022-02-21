package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.domain.Calculator
import edu.nextstep.camp.domain.Expression
import edu.nextstep.camp.domain.Operator

class CalculatorViewModel(val calculator: Calculator) : ViewModel() {

    private val _expression = MutableLiveData(Expression.EMPTY)
    val expression: LiveData<Expression>
        get() = _expression

    private val _errorToast = SingleLiveEvent<Boolean>()
    val errorToast: LiveData<Boolean>
        get() = _errorToast

    fun addToExpression(operand: Int) {
        _expression.value = expression.value?.plus(operand)
    }

    fun addToExpression(operator: Operator) {
        _expression.value = expression.value?.plus(operator)
    }

    fun removeLast() {
        _expression.value = _expression.value?.removeLast()
    }

    fun calculate() {
        val result = calculator.calculate(expression.value.toString())
        if (result == null) {
            _errorToast.value = true
            return
        }
        _expression.value = Expression(listOf(result))
    }
}