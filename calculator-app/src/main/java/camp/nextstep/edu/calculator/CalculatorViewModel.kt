package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator
import camp.nextstep.edu.calculator.domain.data.ResultExpression
import camp.nextstep.edu.calculator.domain.usecase.AddResultExpressionUseCase
import camp.nextstep.edu.calculator.domain.usecase.CalculateUseCase
import camp.nextstep.edu.calculator.domain.usecase.GetResultExpressionListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val dispatchers: CoroutineDispatcher = Dispatchers.IO,
    private val calculateUseCase: CalculateUseCase,
    private val addResultExpressionUseCase: AddResultExpressionUseCase,
    private val getResultExpressionListUseCase: GetResultExpressionListUseCase,
) : ViewModel() {

    private val _expression = MutableLiveData(Expression.EMPTY)
    val expression: LiveData<Expression>
        get() = _expression

    private val _event = MutableLiveData<EventType>()
    val event: LiveData<EventType>
        get() = _event

    private val _memoryVisible = MutableLiveData(false)
    val memoryVisible: LiveData<Boolean>
        get() = _memoryVisible

    private val _resultExpressionItems = MutableLiveData<List<ResultExpression>>()
    val resultExpressionItems: LiveData<List<ResultExpression>>
        get() = _resultExpressionItems

    init {
        viewModelScope.launch(dispatchers) {
            val items: List<ResultExpression> = getResultExpressionListUseCase()
            _resultExpressionItems.postValue(items)
        }
    }

    fun addToExpression(operand: Int) {
        val expressionValue = _expression.value ?: return
        _expression.value = expressionValue + operand
    }

    fun addToExpression(operator: Operator) {
        val expressionValue = _expression.value ?: return
        _expression.value = expressionValue + operator
    }

    fun removeLast() {
        val expressionValue = _expression.value ?: return
        _expression.value = expressionValue.removeLast()
    }

    fun calculate() {
        val expressionValue = _expression.value ?: return

        val result: String? = calculateUseCase(expressionValue.toString())

        result?.let {
            addResultExpression(ResultExpression(expressionValue.toString(), it))
            _expression.value = Expression(listOf(it))
        } ?: run {
            _event.value = EventType.SHOW_TOAST
        }
    }

    private fun addResultExpression(resultExpression: ResultExpression) {
        viewModelScope.launch(dispatchers) {
            addResultExpressionUseCase(resultExpression)
            val items: List<ResultExpression> = getResultExpressionListUseCase()
            _resultExpressionItems.postValue(items)
        }
    }

    fun showMemory() {
        val visible = _memoryVisible.value ?: return
        _memoryVisible.value = !visible
    }

    enum class EventType {
        SHOW_TOAST
    }
}
