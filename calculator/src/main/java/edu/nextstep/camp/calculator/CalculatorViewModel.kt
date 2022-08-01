package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorViewModel(
    private val calculator: Calculator = Calculator()
) : ViewModel() {

    private val _expressionLiveData = MutableLiveData(Expression.EMPTY)
    val expressionLiveData: LiveData<Expression> get() = _expressionLiveData

    private val _failInfo = SingleLiveEvent<Boolean>()
    val failInfo: LiveData<Boolean> get() = _failInfo

    fun addToExpression(operand: Int) {
        _expressionLiveData.value = expressionLiveData.value?.plus(operand)
    }

    fun addToExpression(operator: Operator) {
        _expressionLiveData.value = expressionLiveData.value?.plus(operator)
    }

    fun removeLast() {
        _expressionLiveData.value = expressionLiveData.value?.removeLast()
    }

    fun calculate() {
        runCatching {
            calculator.calculate(expressionLiveData.value.toString())
                ?: throw IllegalArgumentException("완성되지 않은 수식입니다.")
        }.onSuccess { result ->
            _expressionLiveData.value = Expression(listOf(result))
        }.onFailure {
            _failInfo.value = true
        }
    }

}