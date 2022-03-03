package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.nextstep.camp.calculator.data.CalculateRepository
import edu.nextstep.camp.calculator.data.local.History
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.util.SingleLiveEvent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val calculator: Calculator = Calculator(),
    initialExpression: Expression = Expression.EMPTY,
    private val calculatorRepository: CalculateRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    private val _expression: MutableLiveData<Expression> = MutableLiveData(initialExpression)
    val expression: LiveData<Expression>
        get() = _expression

    private val _calculateFailed: MutableLiveData<Unit> = SingleLiveEvent()
    val calculateFailed: LiveData<Unit>
        get() = _calculateFailed

    private val _calculateHistory: MutableLiveData<List<String>?> = SingleLiveEvent()
    val calculateHistory: LiveData<List<String>?>
        get() = _calculateHistory

    fun addToExpression(operand: Int) {
        val expression = _expression.value ?: Expression.EMPTY
        _expression.value = expression.plus(operand)
    }

    fun addToExpression(operator: Operator) {
        val expression = _expression.value ?: Expression.EMPTY
        _expression.value = expression.plus(operator)
    }

    fun calculate() {
        val expression: Expression = _expression.value ?: Expression.EMPTY
        val calculateValue: Int = calculator.calculate(expression.toString())
            ?: kotlin.run {
                _calculateFailed.value = Unit
                return
            }
        _expression.value = Expression.EMPTY + calculateValue

        viewModelScope.launch(ioDispatcher) {
            calculatorRepository.save(History(expression.toString(), calculateValue.toString()))
        }
    }

    fun removeLast() {
        val expression = _expression.value ?: Expression.EMPTY
        _expression.value = expression.removeLast()
    }

    fun showCalculateHistory() {
        viewModelScope.launch(ioDispatcher) {
            val history: List<History> = calculatorRepository.getHistoryAll()
            _calculateHistory.postValue(history.map { getStringForDisplay(it) })
        }
    }

    private fun getStringForDisplay(history: History): String {
        return "${history.formula}\n= ${history.calculateResult}"
    }
}