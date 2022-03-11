package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.domain.Calculator
import edu.nextstep.camp.domain.Expression
import edu.nextstep.camp.domain.Operator
import nextstep.edu.data.CalculatorRepository

class CalculatorViewModel(
    private val calculator: Calculator = Calculator(),
    private val calculatorRepository: CalculatorRepository
) : ViewModel() {

    private val _expression = MutableLiveData(Expression.EMPTY)
    val expression: LiveData<Expression>
        get() = _expression

    private val _errorToast = SingleLiveEvent<Boolean>()
    val errorToast: LiveData<Boolean>
        get() = _errorToast

    private val _isHistoryVisibility = MutableLiveData(false)
    val isHistoryVisibility: LiveData<Boolean>
        get() = _isHistoryVisibility

    private val _historyDataList = MutableLiveData<List<nextstep.edu.data.History>>()
    val historyDataList: MutableLiveData<List<nextstep.edu.data.History>>
        get() = _historyDataList


    fun addToExpression(operand: Int) {
        _expression.value = expression.value?.plus(operand)
    }

    fun addToExpression(operator: Operator) {
        _expression.value = expression.value?.plus(operator)
    }

    fun removeLast() {
        _expression.value = _expression.value?.removeLast()
    }

    fun calculate() {
        val rawExpression = _expression.value.toString()
        val result = calculator.calculate(expression.value.toString())
        if (result == null) {
            _errorToast.value = true
            return
        }

        val newHistory = nextstep.edu.data.History(rawExpression, result)
        saveHistory(newHistory)

        _expression.value = Expression(listOf(result))
    }

    fun toggleHistoryList() {
        _isHistoryVisibility.value = isHistoryVisibility.value?.not()
    }

    private fun saveHistory(newHistory: nextstep.edu.data.History) {
        val thread = Thread {
            calculatorRepository.addHistory(newHistory)
            _historyDataList.postValue(calculatorRepository.getHistoryList())
        }
        thread.start()
    }
}