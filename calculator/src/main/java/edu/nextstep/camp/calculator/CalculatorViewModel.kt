package edu.nextstep.camp.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.nextstep.camp.calculator.domain.CalculatorRepository
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.model.CalculateResult
import edu.nextstep.camp.calculator.domain.model.RecordStatement
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CalculatorViewModel : ViewModel() {
    private val expression = Expression()
    private val calculatorRepository = CalculatorRepository()

    private val _statement = MutableStateFlow("")
    val statement = _statement.asStateFlow()

    private val _errorString = MutableSharedFlow<String>()
    val errorString = _errorString.asSharedFlow()

    private val _memoryViewVisibility = MutableStateFlow(false)
    val memoryViewVisibility = _memoryViewVisibility.asStateFlow()

    private val _recordStatementList = MutableStateFlow<List<RecordStatement>>(emptyList())
    val recordStatementList = _recordStatementList.asStateFlow()

    fun appendOperand(operand: Int) {
        closeMemoryView()
        _statement.value = expression.appendOperand(statement.value, operand.toString())
    }

    fun appendOperator(operator: String) {
        closeMemoryView()
        _statement.value = expression.appendOperator(statement.value, operator) ?: statement.value
    }

    fun deleteLastElement() {
        _statement.value = expression.deleteLastElement(statement.value)
    }

    fun calculateStatement() {
        runCatching {
            closeMemoryView()
            val expressionString = statement.value
            _statement.value = expression.calculatedValue(expressionString)
            val recordStatement = RecordStatement(
                expression = expressionString,
                calculateResult = CalculateResult(statement.value)
            )
            saveStatement(recordStatement)
            _recordStatementList.value = calculatorRepository.getRecordStatement()
        }.onFailure {
            viewModelScope.launch {
                _errorString.emit(it.message!!)
            }
        }
    }

    fun toggleMemoryView() {
        _memoryViewVisibility.value = !memoryViewVisibility.value
    }

    private fun closeMemoryView() {
        _memoryViewVisibility.value = false
    }

    private fun saveStatement(recordStatement: RecordStatement) {
        calculatorRepository.saveStatement(recordStatement)
    }
}