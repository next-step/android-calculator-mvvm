package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.nextstep.camp.calculator.CalculatorEvent.*
import edu.nextstep.camp.calculator.data.CalculationHistoryDatabase
import edu.nextstep.camp.calculator.data.CalculationHistoryEntity
import edu.nextstep.camp.calculator.domain.*
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val calculator: Calculator = Calculator(),
    lastExpression: Expression = DEFAULT_EXPRESSION,
    private var calculationResultStorage: CalculationResultStorage = CalculationResultStorage(),
    lastCalculationHistoryVisibility: Boolean = DEFAULT_CALCULATION_HISTORY_VISIBILITY,
    private val calculationHistoryDB: CalculationHistoryDatabase
) : ViewModel() {
    init {
        requestGetCalculationResultsFromDB()
    }

    private val _isCalculationHistoryVisible: MutableLiveData<Boolean> =
        MutableLiveData(lastCalculationHistoryVisibility)
    val isCalculationHistoryVisible: LiveData<Boolean> = _isCalculationHistoryVisible

    private val _expression = MutableLiveData(lastExpression)
    val expression: LiveData<Expression>
        get() = _expression

    private val _event = MutableLiveData<Event<CalculatorEvent>>()
    val event: LiveData<Event<CalculatorEvent>>
        get() = _event

    private val _calculationResults = MutableLiveData(calculationResultStorage.getResultsAsList())
    val calculationResults: LiveData<List<CalculationResult>>
        get() = _calculationResults

    fun addOperandToExpression(operand: Int) {
        val newExpression = getExpressionOrDefault() + operand
        _expression.value = newExpression
    }

    fun addOperatorToExpression(operator: Operator) {
        val newExpression = getExpressionOrDefault() + operator
        _expression.value = newExpression
    }

    fun removeLastFromExpression() {
        val newExpression = getExpressionOrDefault().removeLast()
        _expression.value = newExpression
    }

    fun toggleCalculationHistoryVisibility() {
        if (isCalculationHistoryVisible.value ?: DEFAULT_CALCULATION_HISTORY_VISIBILITY) {
            _isCalculationHistoryVisible.value = false
            return
        }
        _isCalculationHistoryVisible.value = true
        requestCalculationResultsUpdate()
    }

    fun requestCalculate() {
        val inputtedExpression = getExpressionOrDefault()
        if (!inputtedExpression.isCompletedExpression()) {
            _event.value = Event(ERROR_INCOMPLETE_EXPRESSION)
            return
        }
        val result = calculator.calculate(inputtedExpression.toString())
        if (result == null) {
            _event.value = Event(ERROR_INCOMPLETE_EXPRESSION)
            return
        }
        processCalculationResult(CalculationResult(inputtedExpression, result))
    }

    private fun requestGetCalculationResultsFromDB() {
        viewModelScope.launch {
            val savedCalculationHistory =
                calculationHistoryDB.calculationHistoryDao()
                    .getAll()
                    .map(CalculationHistoryEntity::toCalculationResult)
            calculationResultStorage += savedCalculationHistory
        }
    }

    private fun processCalculationResult(calculationResult: CalculationResult) {
        addCalculationResultToStorage(calculationResult)
        putCalculationResultToDB(calculationResult)
        val newExpression = Expression.EMPTY + calculationResult.result
        _expression.value = newExpression
    }

    private fun putCalculationResultToDB(calculatedResult: CalculationResult) {
        fun CalculationResult.toCalculationHistoryEntity() =
            CalculationHistoryEntity(
                expression.toString(),
                result
            )
        viewModelScope.launch {
            calculationHistoryDB.calculationHistoryDao()
                .insert(calculatedResult.toCalculationHistoryEntity())
        }
    }

    private fun getExpressionOrDefault(): Expression = expression.value ?: DEFAULT_EXPRESSION

    private fun addCalculationResultToStorage(calculationResult: CalculationResult) {
        calculationResultStorage += calculationResult
    }

    fun requestCalculationResultsUpdate() {
        _calculationResults.value = calculationResultStorage.getResultsAsList()
    }

    companion object {
        private val DEFAULT_EXPRESSION = Expression.EMPTY
        private const val DEFAULT_CALCULATION_HISTORY_VISIBILITY = false
    }
}