package edu.nextstep.camp.calculator

import androidx.lifecycle.*
import edu.nextstep.camp.domain.calculator.CalculationHistory
import edu.nextstep.camp.domain.calculator.Calculator
import edu.nextstep.camp.domain.calculator.Expression
import edu.nextstep.camp.domain.calculator.Operator
import edu.nextstep.camp.domain.calculator.usecase.GetAllCalculationHistoryUseCase
import edu.nextstep.camp.domain.calculator.usecase.InsertCalculationHistoryUseCase
import edu.nextstep.camp.domain.counter.Counter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val insertCalculationHistory: InsertCalculationHistoryUseCase,
    private val getAllCalculationHistory: GetAllCalculationHistoryUseCase
): ViewModel() {
    private val calculator = Calculator()
    private val _expression = MutableLiveData(Expression.EMPTY)
    val expression: LiveData<Expression> = _expression

    private val _errorMessage = SingleLiveEvent<UiText>()
    val errorMessage: LiveData<UiText> = _errorMessage

    private val _calculationHistoryList = MutableLiveData<List<CalculationHistory>>()
    val calculationHistoryList: LiveData<List<CalculationHistory>> = _calculationHistoryList

    private val _uiMode = MutableLiveData(CalculatorUiMode.CALCULATOR)
    val uiMode: LiveData<CalculatorUiMode> = _uiMode

    init {
        loadCalculationHistory()
    }

    private fun loadCalculationHistory() {
        getAllCalculationHistory()
            .onEach { _calculationHistoryList.value = it }
            .launchIn(viewModelScope)
    }

    fun toggleUiBetweenCalculatorOrHistory() {
        _uiMode.value = when (uiMode.value) {
            CalculatorUiMode.CALCULATOR -> CalculatorUiMode.CALCULATION_HISTORY
            else -> CalculatorUiMode.CALCULATOR
        }
    }

    fun addToExpression(number: Int) {
        _expression.value = getCurrentExpression() + number
    }

    fun addToExpression(operator: Operator) {
        _expression.value = getCurrentExpression() + operator
    }

    fun removeLast() {
        _expression.value = getCurrentExpression().removeLast()
    }

    fun calculate() {
        val rawExpressionString = getCurrentExpression().toString()
        val result = calculator.calculate(rawExpressionString)
        if (result == null) {
            showIncompleteExpressionError()
        } else {
            saveCalculationHistory(getCurrentExpression(), result)
            _expression.value = Expression(listOf(result))
        }
    }

    private fun saveCalculationHistory(currentExpression: Expression, result: Int) {
        viewModelScope.launch {
            insertCalculationHistory(CalculationHistory(CalculationHistory.INVALID_ID, currentExpression, result))
        }
    }

    private fun showIncompleteExpressionError() {
        _errorMessage.value = UiText.StringResource(R.string.incomplete_expression)
    }

    private fun getCurrentExpression() = _expression.value ?: Expression.EMPTY
}

class CalculatorViewModelFactory(
    private val insertCalculationHistoryUseCase: InsertCalculationHistoryUseCase,
    private val getAllCalculationHistoryUseCase: GetAllCalculationHistoryUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalculatorViewModel::class.java)) {
            return CalculatorViewModel(insertCalculationHistoryUseCase, getAllCalculationHistoryUseCase) as T
        }
        throw IllegalArgumentException("Not found ViewModel class.")
    }
}