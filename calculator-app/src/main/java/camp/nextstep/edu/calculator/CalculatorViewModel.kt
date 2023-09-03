package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.CalculatorRepositoryInterface
import camp.nextstep.edu.calculator.domain.CalculationResult
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CalculatorViewModel(
    expression: Expression = Expression.EMPTY,
    private val calculatorRepository: CalculatorRepositoryInterface
) : ViewModel() {

    private val calculator = Calculator()

    private val _expression = MutableLiveData(expression)
    val expression: LiveData<Expression> = _expression

    private val _inCompleteExpressionEvent = SingleLiveEvent<Unit>()
    val inCompleteExpressionEvent: LiveData<Unit> = _inCompleteExpressionEvent

    private val _historyVisibleState = MutableLiveData(false)
    val historyVisibleState: LiveData<Boolean> = _historyVisibleState

    private val _calculationHistories = MutableLiveData<List<CalculationResult>>()
    val calculationHistories: LiveData<List<CalculationResult>> = _calculationHistories

    fun onClickHistoryBtn() {
        _historyVisibleState.value = historyVisibleState.value?.not() ?: false
        if (historyVisibleState.value == true) showHistories()
    }

    private fun showHistories(
        dispatcher: CoroutineContext = Dispatchers.IO
    ) {
        viewModelScope.launch(dispatcher) {
            _calculationHistories.postValue(calculatorRepository.getCalculatorResults())
        }
    }

    fun addToExpression(operand: Int) {
        _expression.value = expression.value?.plus(operand)
    }

    fun addToExpression(operator: Operator) {
        _expression.value = expression.value?.plus(operator)
    }

    fun removeLast() {
        _expression.value = expression.value?.removeLast()
    }

    fun calculate() {
        val result = calculator.calculate(expression.value.toString())
        if (result == null) {
            _inCompleteExpressionEvent.call()
        } else {
            saveCalculationResult(
                expression = expression.value,
                result = result
            )
            _expression.value = Expression(listOf(result))
        }
    }

    private fun saveCalculationResult(
        dispatcher: CoroutineContext = Dispatchers.IO,
        expression: Expression?,
        result: Int?
    ) {
        viewModelScope.launch(dispatcher) {
            calculatorRepository.insertCalculatorResult(
                CalculationResult(
                    expression = expression.toString(),
                    result = result.toString()
                )
            )
        }
    }
}