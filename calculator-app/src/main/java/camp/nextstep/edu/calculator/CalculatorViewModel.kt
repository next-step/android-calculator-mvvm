package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator
import camp.nextstep.edu.calculator.domain.repository.CalculatorRepository

class CalculatorViewModel(
    calculatorRepository: CalculatorRepository
) : ViewModel() {
    private var _textInTextView = MutableLiveData<String>()
    val textInTextView: LiveData<String>
        get() = _textInTextView

    private var _expression = MutableLiveData(Expression.EMPTY)
    val expression: LiveData<Expression>
        get() = _expression

    private var _result = MutableLiveData<Int>()
    val result: LiveData<Int>
        get() = _result

    private var _onCalculationErrorEvent = SingleLiveEvent<Unit>()
    val onCalculationErrorEvent
        get() = _onCalculationErrorEvent

    private val calculator = Calculator()

    fun addToExpression(operand: Int) {
        _expression.value = _expression.value?.plus(operand)
        _textInTextView.value = _expression.value.toString()
    }

    fun addToExpression(operator: Operator) {
        _expression.value = _expression.value?.plus(operator)
        _textInTextView.value = _expression.value.toString()
    }

    fun removeLast() {
        _expression.value = _expression.value?.removeLast()
        _textInTextView.value = _expression.value.toString()
    }

    fun calculate() {
        val calculateResult = calculator.calculate(_expression.value.toString())

        if (calculateResult == null) {
            _onCalculationErrorEvent.value = Unit
        } else {
            _result.value = calculateResult!!
            _textInTextView.value = _result.value.toString()
        }
    }
}