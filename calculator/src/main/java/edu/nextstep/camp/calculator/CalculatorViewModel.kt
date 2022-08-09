package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.nextstep.camp.calculator.domain.*
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val historyRepository: HistoryRepository,
    private val calculator: Calculator = Calculator()
) : ViewModel() {

    private val _expression = MutableLiveData(Expression.EMPTY)
    val expression: LiveData<Expression> get() = _expression

    private val _failInfo = SingleLiveEvent<Boolean>()
    val failInfo: LiveData<Boolean> get() = _failInfo

    private val _isShowHistories = MutableLiveData(false)
    val isShowHistories: LiveData<Boolean> get() = _isShowHistories

    private val _histories = MutableLiveData<List<History>>()
    val histories: LiveData<List<History>> = _histories

    fun fetchHistories() = viewModelScope.launch {
        _histories.value = historyRepository.getAllHistories()
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
        runCatching {
            calculator.calculate(expression.value.toString())
                ?: throw IllegalArgumentException("완성되지 않은 수식입니다.")
        }.onSuccess { result ->
            viewModelScope.launch {
                _histories.value = historyRepository.addHistory(
                    History(expression.value ?: Expression(), result.toString())
                )
            }

            _expression.value = Expression(listOf(result))
        }.onFailure {
            _failInfo.value = true
        }
    }

    fun toggleHistories() {
        _isShowHistories.value = isShowHistories.value?.not()
    }

}