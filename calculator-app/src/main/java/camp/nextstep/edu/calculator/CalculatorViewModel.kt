package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import camp.nextstep.edu.calculator.domain.ArithmeticExpression
import camp.nextstep.edu.calculator.domain.ArithmeticOperator
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression

class CalculatorViewModel(private val expression: Expression) : ViewModel() {

    private val _currentExpression = MutableLiveData(expression.value)
    val currentExpression: LiveData<String> = _currentExpression

    private val _showWarningMessageEvent = SingleLiveEvent<String>()
    val showWarningMessageEvent: LiveData<String> = _showWarningMessageEvent

    fun setOperand(operand: Int) {
        expression.setOperand(operand)
        _currentExpression.value = expression.value
    }

    fun setOperator(operator: ArithmeticOperator) {
        expression.setOperator(operator)
        _currentExpression.value = expression.value
    }

    fun calculate() {
        kotlin.runCatching {
            Calculator.calculate(ArithmeticExpression(expression.value))
        }.onSuccess { result ->
            expression.setEquals(result)
            _currentExpression.value = expression.value
        }.onFailure { exception ->
            showWarningMessage(exception.message ?: "")
        }
    }

    fun delete() {
        expression.setDelete()
        _currentExpression.value = expression.value
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
