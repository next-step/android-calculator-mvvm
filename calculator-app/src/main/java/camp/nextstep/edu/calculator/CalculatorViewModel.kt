package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import camp.nextstep.edu.calculator.domain.ArithmeticExpression
import camp.nextstep.edu.calculator.domain.ArithmeticOperator
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression

class CalculatorViewModel(expression: Expression) : ViewModel() {

    private val _expression = MutableLiveData(expression)
    val expression: LiveData<Expression> = _expression

    private val _showWarningMessageEvent = SingleLiveEvent<String>()
    val showWarningMessageEvent: LiveData<String> = _showWarningMessageEvent

    fun addOperand(operand: Int) {
        _expression.value = expression.value?.addOperand(operand)
    }

    fun addOperator(operator: ArithmeticOperator) {
        _expression.value = expression.value?.addOperator(operator)
    }

    fun calculate() {
        kotlin.runCatching {
            Calculator.calculate(ArithmeticExpression(expression.value?.value ?: ""))
        }.onSuccess { result ->
            _expression.value = expression.value?.setEquals(result)
        }.onFailure { exception ->
            showWarningMessage(exception.message ?: "")
        }
    }

    fun delete() {
        _expression.value = expression.value?.setDelete()
    }

    private fun showWarningMessage(message: String) {
        _showWarningMessageEvent.value = message
    }
}

class CalculatorViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            CalculatorViewModel::class.java -> CalculatorViewModel(Expression(""))

            else -> throw IllegalStateException()
        } as T
    }
}
