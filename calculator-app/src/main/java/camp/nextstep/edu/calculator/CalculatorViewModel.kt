package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator
import camp.nextstep.edu.calculator.domain.model.History
import camp.nextstep.edu.calculator.domain.usecase.GetCalculateHistoriesUseCase
import camp.nextstep.edu.calculator.domain.usecase.PostCalculateUseCase
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val postCalculateUseCase: PostCalculateUseCase,
    private val getCalculateHistoriesUseCase: GetCalculateHistoriesUseCase
): ViewModel() {

    private val calculator = Calculator()
    private var expression: Expression = Expression.EMPTY

    private val _text: MutableLiveData<String> = MutableLiveData()
    val text: LiveData<String> = _text

    private val _inCompleteExpressionError: MutableLiveData<Event<String>> = MutableLiveData()
    val inCompleteExpressionError: LiveData<Event<String>> = _inCompleteExpressionError

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
        viewModelScope.launch {
            postCalculateUseCase(calculator = calculator, expression = expression)
                .onSuccess {
                    expression = Expression(listOf(it))
                    setText(expression)
                }.onFailure {
                    _inCompleteExpressionError.value = Event(it.message ?: "알 수 없는 에러입니다.")
                }
        }
    }

    fun toggleHistory() {
        _historyVisible.value = !(_historyVisible.value ?: false)

        if (historyVisible.value == true) {
            getHistories()
        }
    }

    private fun getHistories() {
        viewModelScope.launch {
            _histories.postValue(getCalculateHistoriesUseCase().orEmpty())
        }
    }

    private fun setText(expression: Expression) {
        _text.postValue(expression.toString())
    }
}