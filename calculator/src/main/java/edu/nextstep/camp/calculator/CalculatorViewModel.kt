package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

/**
 * Created by link.js on 2022. 07. 28..
 */
class CalculatorViewModel : ViewModel() {
    private val calculator = Calculator()
    private var _expression = MutableLiveData(Expression.EMPTY)
    val expression: LiveData<Expression>
        get() = _expression

    fun addToExpression(operand: Int) {
        _expression.postValue(_expression.value?.plus(operand))
    }

    fun addToExpression(operator: Operator) {
        _expression.postValue(_expression.value?.plus(operator))
    }

    fun removeLast() {
        _expression.postValue(_expression.value?.removeLast())
    }

    fun calculate() {
        val result = calculator.calculate(_expression.value.toString())
        if (result == null) {
            //view.showIncompleteExpressionError()
        } else {
            _expression.postValue(Expression(listOf(result)))
        }
    }
}
