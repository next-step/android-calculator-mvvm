package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import camp.nextstep.edu.calculator.domain.model.Calculator
import camp.nextstep.edu.calculator.domain.model.Expression
import camp.nextstep.edu.calculator.domain.model.Operator
import camp.nextstep.edu.calculator.domain.model.Record
import camp.nextstep.edu.calculator.domain.repository.RecordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val recordRepository: RecordRepository
) : ViewModel() {
    private val calculator = Calculator()
    private val _expression: MutableLiveData<Expression> = MutableLiveData(Expression.EMPTY)
    val expression: LiveData<Expression>
        get() = _expression
    private val _isExpressionError: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val isExpressionError: LiveData<Boolean> = _isExpressionError
    private var isShowRecord: Boolean = false

    fun addToExpression(operand: Int) {
        if (!isShowRecord) {
            _expression.value = _expression.value?.plus(operand)
        }
    }

    fun addToExpression(operator: Operator) {
        if (!isShowRecord) {
            _expression.value = _expression.value?.plus(operator)
        }
    }

    fun removeLast() {
        if (!isShowRecord) {
            _expression.value = _expression.value?.removeLast()
        }
    }

    fun calculate() {
        val result = calculator.calculate(_expression.value.toString())
        if (result == null) {
            _isExpressionError.value = true
        } else {
            _expression.value?.let {
                saveRecord(Record(it, result))
            }
            _expression.value = Expression(listOf(result))
        }
    }

    fun saveRecord(record: Record) {
        viewModelScope.launch(Dispatchers.IO) {
            recordRepository.saveRecord(record)
        }
    }

    fun loadRecords() {
        if (!isShowRecord) {
            viewModelScope.launch(Dispatchers.IO) {
                val record: List<Char> = recordRepository.loadRecords()
                    .joinToString("\n") {
                        "${it.expression}\n = ${it.result}"
                    }.toList()
                _expression.postValue(Expression(record))
            }
        } else {
            _expression.value = Expression.EMPTY
        }
        isShowRecord = !isShowRecord
    }
}
