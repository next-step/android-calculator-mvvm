package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.nextstep.camp.calculator.data.CalculationHistoryEntity
import edu.nextstep.camp.calculator.data.HistoryRepository
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val historyRepository: HistoryRepository,
    private val calculator: Calculator = Calculator()
) : ViewModel() {

    private val _expressionLiveData = MutableLiveData(Expression.EMPTY)
    val expressionLiveData: LiveData<Expression> get() = _expressionLiveData

    private val _failInfo = SingleLiveEvent<Boolean>()
    val failInfo: LiveData<Boolean> get() = _failInfo

    private val _isShowHistoriesLiveData = MutableLiveData(false)
    val isShowHistoriesLiveData: LiveData<Boolean> get() = _isShowHistoriesLiveData

    private val _historiesLiveData = MutableLiveData<List<CalculationHistoryEntity>>()
    val historiesLiveData: LiveData<List<CalculationHistoryEntity>> = _historiesLiveData

    init {
        fetchHistories()
    }

    private fun fetchHistories() = viewModelScope.launch {
        _historiesLiveData.value = historyRepository.getAllHistories()
    }

    fun addToExpression(operand: Int) {
        _expressionLiveData.value = expressionLiveData.value?.plus(operand)
    }

    fun addToExpression(operator: Operator) {
        _expressionLiveData.value = expressionLiveData.value?.plus(operator)
    }

    fun removeLast() {
        _expressionLiveData.value = expressionLiveData.value?.removeLast()
    }

    fun calculate() {
        runCatching {
            calculator.calculate(expressionLiveData.value.toString())
                ?: throw IllegalArgumentException("완성되지 않은 수식입니다.")
        }.onSuccess { result ->
            viewModelScope.launch {
                historyRepository.addHistory(
                    CalculationHistoryEntity(
                        expression = expressionLiveData.value ?: Expression(),
                        result = result.toString()
                    )
                )

                fetchHistories()
            }

            _expressionLiveData.value = Expression(listOf(result))
        }.onFailure {
            _failInfo.value = true
        }
    }

    fun toggleHistories() {
        _isShowHistoriesLiveData.value = isShowHistoriesLiveData.value?.not()
    }

}