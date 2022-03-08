package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.nextstep.camp.domain.CalculatorHistoryData
import edu.nextstep.camp.domain.CalculatorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalculatorViewModel(private val calculatorRepository: CalculatorRepository) : ViewModel() {
    private val calculator = edu.nextstep.camp.domain.Calculator()
    private var expression = edu.nextstep.camp.domain.Expression.EMPTY

    private val _text = MutableLiveData<String>()
    val text: LiveData<String>
        get() = _text

    private val _showErrorMessage = MutableLiveData<Event<Unit>>()
    val showErrorMessage: LiveData<Event<Unit>> = _showErrorMessage

    private val _isVisibleHistoryList = MutableLiveData(false)
    val isVisibleHistoryList: LiveData<Boolean> = _isVisibleHistoryList

    private val _historyList = MutableLiveData<List<CalculatorHistoryData>>(emptyList())
    val historyList: LiveData<List<CalculatorHistoryData>> = _historyList


    fun addToExpression(operand: Int) {
        expression += operand
        _text.value = expression.toString()
    }

    fun addToExpression(operator: edu.nextstep.camp.domain.Operator) {
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

        val newHistory = CalculatorHistoryData(expression.toString(), result.toString())
        viewModelScope.launch(Dispatchers.IO) {
            calculatorRepository.addHistory(newHistory)

        }
    }

    fun showOrHideHistoryList() {
        _isVisibleHistoryList.value = _isVisibleHistoryList.value != true

        if (_isVisibleHistoryList.value == false) {
            viewModelScope.launch(Dispatchers.IO) {
                _historyList.postValue(calculatorRepository.getHistory())

            }
        }
    }

}