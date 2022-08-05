package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.nextstep.camp.calculator.CalculatorEvent.*
import edu.nextstep.camp.calculator.data.CalculationResultDatabase
import edu.nextstep.camp.calculator.data.CalculationResultEntity
import edu.nextstep.camp.calculator.domain.*
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val calculator: Calculator = Calculator(),
    lastExpression: Expression = DEFAULT_EXPRESSION,
    private var calculationResultStorage: CalculationResultStorage = CalculationResultStorage(),
    lastCalculationHistoryVisibility: Boolean = DEFAULT_calculation_result_VISIBILITY,
    private val calculationResultDB: CalculationResultDatabase
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
        if (isCalculationHistoryVisible.value ?: DEFAULT_calculation_result_VISIBILITY) {
            _isCalculationHistoryVisible.value = false
            return
        }
        _isCalculationHistoryVisible.value = true
        sendCalculationResultsToView()
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
                calculationResultDB.calculationResultDao()
                    .getAll()
                    .map(CalculationResultEntity::toCalculationResult).forEach {
                        calculationResultStorage += it
                    }
        }
    }

    private fun processCalculationResult(calculationResult: CalculationResult) {
        addCalculationResultToStorage(calculationResult)
        putCalculationResultToDB(calculationResult)
        val newExpression = Expression.EMPTY + calculationResult.result
        _expression.value = newExpression
    }

    private fun putCalculationResultToDB(calculatedResult: CalculationResult) {
        fun CalculationResult.toCalculationResultEntity() =
            CalculationResultEntity(
                expression.toString(),
                result
            )
        viewModelScope.launch {
            calculationResultDB.calculationResultDao()
                .insert(calculatedResult.toCalculationResultEntity())
        }
    }

    private fun getExpressionOrDefault(): Expression = expression.value ?: DEFAULT_EXPRESSION

    private fun addCalculationResultToStorage(calculationResult: CalculationResult) {
        calculationResultStorage += calculationResult
    }

    fun sendCalculationResultsToView() {
        _calculationResults.value = calculationResultStorage.getResultsAsList()
    }

    companion object {
        private val DEFAULT_EXPRESSION = Expression.EMPTY
        private const val DEFAULT_calculation_result_VISIBILITY = false
    }
}