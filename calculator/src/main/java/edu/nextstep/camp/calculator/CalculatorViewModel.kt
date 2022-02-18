package edu.nextstep.camp.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.nextstep.camp.calculator.domain.CalculatorRepository
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.model.RecordStatement
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val expression: Expression,
    private val calculatorRepository: CalculatorRepository
) : ViewModel() {

    private val _statement = MutableStateFlow("")
    val statement = _statement.asStateFlow()

    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage = _errorMessage.asSharedFlow()

    private val _memoryViewVisibility = MutableStateFlow(false)
    val memoryViewVisibility = _memoryViewVisibility.asStateFlow()

    fun appendOperand(operand: Int) {
        closeMemoryView()
        _statement.value = expression.appendOperand(statement.value, operand.toString())
    }

    fun appendOperator(operator: String) {
        closeMemoryView()
        _statement.value = expression.appendOperator(statement.value, operator) ?: statement.value
    }

    fun deleteLastElement() {
        closeMemoryView()
        _statement.value = expression.deleteLastElement(statement.value)
    }

    fun calculateStatement() {
        runCatching {
            closeMemoryView()
            val expressionString = statement.value
            _statement.value = expression.calculatedValue(expressionString)
            val recordStatement = RecordStatement(
                expression = expressionString,
                calculateResult = statement.value
            )
            saveStatement(recordStatement)
            viewModelScope.launch {
                getStatements()
            }
        }.onFailure {
            viewModelScope.launch {
                it.message?.let { _errorMessage.emit(it) }
            }
        }
    }

    suspend fun getStatements(): Flow<List<RecordStatement>> =
        calculatorRepository.getStatements()

    private fun saveStatement(recordStatement: RecordStatement) = viewModelScope.launch {
        calculatorRepository.saveStatement(recordStatement)
    }

    fun toggleMemoryView() {
        _memoryViewVisibility.value = !memoryViewVisibility.value
    }

    private fun closeMemoryView() {
        _memoryViewVisibility.value = false
    }
}
