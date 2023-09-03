package camp.nextstep.edu.calculator

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import camp.nextstep.edu.calculator.domain.ArithmeticExpression
import camp.nextstep.edu.calculator.domain.ArithmeticOperator
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.ResultRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CalculatorViewModel(
    expression: Expression,
    private val resultRepository: ResultRepository,
) : ViewModel() {

    private val _expression = MutableLiveData(expression)
    val expression: LiveData<Expression> = _expression

    private val _isResultListVisible = MutableLiveData(false)
    val isResultListVisible: LiveData<Boolean> = _isResultListVisible

    val resultList = resultRepository.resultList.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = emptyList()
    ).asLiveData()

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
            viewModelScope.launch {
                resultRepository.putResult(expression.value?.value ?: "", result)
                _expression.value = expression.value?.setEquals(result)
            }
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

    fun toggleResultListVisible() {
        _isResultListVisible.value = isResultListVisible.value == false
    }
}

class CalculatorViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            CalculatorViewModel::class.java -> CalculatorViewModel(
                expression = Expression(""),
                resultRepository = context.container.resultRepository
            )

            else -> throw IllegalStateException()
        } as T
    }
}
