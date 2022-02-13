package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.CalculatorRepository
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.model.CalculateResult
import edu.nextstep.camp.calculator.domain.model.RecordStatement

class CalculatorViewModel : ViewModel() {
    private val expression = Expression()
    private val calculatorRepository = CalculatorRepository()

    private val _statement = MutableLiveData<String>()
    val statement: LiveData<String>
        get() = _statement
    private val _errorString = SingleLiveEvent<String>()
    val errorString: SingleLiveEvent<String>
        get() = _errorString
    private val _memoryViewVisibility = SingleLiveEvent<Boolean>()
    val memoryViewVisibility: SingleLiveEvent<Boolean>
        get() = _memoryViewVisibility
    private val _recordStatement = MutableLiveData<RecordStatement>()
    val recordStatement: LiveData<RecordStatement>
        get() = _recordStatement

    fun appendOperand(operand: Int) {
        _memoryViewVisibility.value = false
        _statement.value = expression.appendOperand(statement.value ?: "", operand.toString())
    }

    fun appendOperator(operator: String) {
        _memoryViewVisibility.value = false
        _statement.value = expression.appendOperator(statement.value ?: "", operator)
    }

    fun deleteLastElement() {
        _statement.value = expression.deleteLastElement(statement.value ?: "")
    }

    fun calculateStatement() {
        runCatching {
            val expressionString = statement.value ?: ""
            _statement.value = expression.calculatedValue(expressionString)
            val recordStatement = RecordStatement(
                expression = expressionString,
                calculateResult = CalculateResult(statement.value ?: "")
            )
            _recordStatement.value = recordStatement
            saveStatement(recordStatement)
        }.onFailure {
            _errorString.value = it.message
        }
    }

    fun toggleMemoryView() {
        memoryViewVisibility.value?.let { _memoryViewVisibility.value = !it }
    }

    private fun saveStatement(recordStatement: RecordStatement) {
        calculatorRepository.saveStatement(recordStatement)
    }
}