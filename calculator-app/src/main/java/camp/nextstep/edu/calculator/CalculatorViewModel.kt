package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.calculator.domain.model.Calculator
import camp.nextstep.edu.calculator.domain.model.Expression
import camp.nextstep.edu.calculator.domain.model.Operator
import camp.nextstep.edu.calculator.domain.model.Record
import camp.nextstep.edu.calculator.domain.repository.RecordRepository

class CalculatorViewModel(
    private val recordRepository: RecordRepository
) : ViewModel() {
    private val calculator = Calculator()
    private val _expression: MutableLiveData<Expression> = MutableLiveData(Expression.EMPTY)
    val expression: LiveData<Expression> = _expression
    private val _isExpressionError: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val isExpressionError: LiveData<Boolean> = _isExpressionError

    fun addToExpression(operand: Int) {
        _expression.value = _expression.value?.plus(operand)
    }

    fun addToExpression(operator: Operator) {
        _expression.value = _expression.value?.plus(operator)
    }

    fun removeLast() {
        _expression.value = _expression.value?.removeLast()
    }

    fun calculate() {
        val result = calculator.calculate(_expression.value.toString())
        if (result == null) {
            _isExpressionError.value = true
        } else {
            _expression.value = Expression(listOf(result))
        }
    }

    private fun saveRecord(record: Record) {
        recordRepository.saveRecord(record)
    }

    private fun loadRecord() {
//        return recordRepository.loadRecords()
    }
}
