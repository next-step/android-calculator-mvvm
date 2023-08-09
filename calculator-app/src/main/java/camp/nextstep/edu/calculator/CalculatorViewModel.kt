package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator
import camp.nextstep.edu.calculator.domain.model.History
import camp.nextstep.edu.calculator.domain.usecase.GetCalculateHistoriesUseCase
import camp.nextstep.edu.calculator.domain.usecase.PostCalculateUseCase

class CalculatorViewModel(
    private val postCalculateUseCase: PostCalculateUseCase,
    private val getCalculateHistoriesUseCase: GetCalculateHistoriesUseCase
): ViewModel() {

    private val calculator = Calculator()
    private var expression: Expression = Expression.EMPTY

    private val _text: MutableLiveData<String> = MutableLiveData()
    val text: LiveData<String> = _text

    private val _inCompleteExpressionError: MutableLiveData<Event<Unit>> = MutableLiveData()
    val inCompleteExpressionError: LiveData<Event<Unit>> = _inCompleteExpressionError

    private val _historyVisible: MutableLiveData<Boolean> = MutableLiveData(false)
    val historyVisible: LiveData<Boolean> = _historyVisible

    private val _histories: MutableLiveData<List<History>> = MutableLiveData()
    val histories: LiveData<List<History>> = _histories

    fun addToExpression(operand: Int) {
        expression += operand
        setText(expression)
    }

    fun addToExpression(operator: Operator) {
        expression += operator
        setText(expression)
    }

    fun removeLast() {
        expression = expression.removeLast()
        setText(expression)
    }

    fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            _inCompleteExpressionError.value = Event(Unit)
        } else {
            postCalculateUseCase(History(expressions = expression.toString(), result = result))
            expression = Expression(listOf(result))
            setText(expression)
        }
    }

    fun toggleHistory() {
        _historyVisible.value = !(_historyVisible.value ?: false)

        if (historyVisible.value == true) {
            getHistories()
        }
    }

    private fun getHistories() {
        _histories.postValue(getCalculateHistoriesUseCase().orEmpty())
    }

    private fun setText(expression: Expression) {
        _text.postValue(expression.toString())
    }
}