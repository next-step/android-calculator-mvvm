package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator

class CalculatorViewModel : ViewModel() {
    private val _expression = MutableLiveData<Expression>().apply { value = Expression.EMPTY }
    val expression: LiveData<Expression>
        get() = _expression

    fun addToExpression(operand: Int) {

    }

    fun addToExpression(operator: Operator) {

    }

    fun removeLast() {

    }

    fun calculate() {

    }
}