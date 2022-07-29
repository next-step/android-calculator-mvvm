package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorViewModel(
    private var expression: Expression = Expression.EMPTY,
    private val calculator: Calculator = Calculator()
) : ViewModel() {

    private val _showingExpression = MutableLiveData<String>()
    val showingExpression: LiveData<String>
        get() = _showingExpression

    fun addToExpression(operand: Int) {
        expression += operand
        _showingExpression.value = expression.toString()
    }

    fun addToExpression(operator: Operator) {
        TODO()
    }

    fun removeLast() {
        TODO()
    }

    fun calculate() {
        TODO()
    }
}
