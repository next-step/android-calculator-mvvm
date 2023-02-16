package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator

class CalculatorViewModel : ViewModel() {

    private var _result = MutableLiveData<String>()
    val result: LiveData<String> get() = _result

    private var expression = Expression.EMPTY
    private val calculator = Calculator()

    private var _showError = SingleLiveEvent<Unit>()
    val showError : LiveData<Unit> get() = _showError

    fun addOperand(operand: Int) {
        expression += operand
        updateUiState()
    }

    fun addOperator(operator: Operator) {
        expression += operator
        updateUiState()
    }

    fun removeLast() {
        expression = expression.removeLast()
        updateUiState()
    }

    fun calculate() {
        val calculatedValue = calculator.calculate(expression.toString())

        if (calculatedValue == null) {
            _showError.call()
            return
        }

        expression = Expression(listOf(calculatedValue))
        updateUiState()
    }

    private fun updateUiState() {
        _result.value = expression.toString()
    }
}