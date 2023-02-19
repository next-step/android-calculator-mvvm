package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator
import camp.nextstep.edu.calculator.domain.RecordRepository
import camp.nextstep.edu.calculator.domain.model.Record
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val recordRepository: RecordRepository
) : ViewModel() {

    private val _text = MutableLiveData<String>()
    val text: LiveData<String>
        get() = _text

    private val _showToastMessage = MutableLiveData<Unit>()
    val showToastMessage: LiveData<Unit>
        get() = _showToastMessage

    private val calculator = Calculator()
    private var expression = Expression.EMPTY


    fun addToExpression(operand: Int) {
        expression += operand
        _text.value = expression.toString()
    }

    fun addToExpression(operator: Operator) {
        expression += operator
        _text.value = expression.toString()
    }

    private fun saveRecord(record: Record) {
        viewModelScope.launch(Dispatchers.IO) {
            recordRepository.insertRecord(record)
        }

    }

    fun getRecords() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = recordRepository.getRecord()
                .joinToString("\n") { "${it.expression}\n = ${it.result}" }
            _text.postValue(result)
        }

    }

    fun removeLast() {
        expression = expression.removeLast()
        _text.value = expression.toString()
    }

    fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result != null) {
            saveRecord(Record(expression.toString(), result))
            expression = Expression(listOf(result))
            _text.value = expression.toString()
        } else {
            _showToastMessage.value = Unit
        }

    }

}