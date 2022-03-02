package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.data.CalculatorDao
import edu.nextstep.camp.data.CalculatorHisotry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalculatorViewModel(private val calculatorDao: CalculatorDao) :
    ViewModel() {
    private val calculator = Calculator()
    private var expression = Expression.EMPTY

    private val _text = MutableLiveData<String>()
    val text: LiveData<String>
        get() = _text

    private val _showErrorMessage = MutableLiveData<Event<Unit>>()
    val showErrorMessage: LiveData<Event<Unit>> = _showErrorMessage

    private val _isVisibleHistoryList = MutableLiveData(false)
    val isVisibleHistoryList: LiveData<Boolean> = _isVisibleHistoryList

    private val _historyList = MutableLiveData<List<CalculatorHisotry>>(emptyList())
    val historyList: LiveData<List<CalculatorHisotry>> = _historyList


    fun addToExpression(operand: Int) {
        expression += operand
        _text.value = expression.toString()
    }

    fun addToExpression(operator: Operator) {
        expression += operator
        _text.value = expression.toString()
    }

    fun removeLast() {
        expression = expression.removeLast()
        _text.value = expression.toString()
    }

    fun calculator() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            _showErrorMessage.value = Event(Unit)
            return
        }
        _text.value = result.toString()

        val newHistory = CalculatorHisotry(expression.toString(), result.toString())
        CoroutineScope(Dispatchers.IO).launch {
            calculatorDao.insertCalculatorHisotry(newHistory)
        }
    }

    fun showOrHideHistoryList() {
        _isVisibleHistoryList.value = _isVisibleHistoryList.value != true

        if (_isVisibleHistoryList.value == false) {
            CoroutineScope(Dispatchers.IO).launch {
                _historyList.postValue(calculatorDao.getCalculatorHisotry())
            }
        }
    }

}