package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextstep.camp.calculator.data.RecordsRepository
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Operand
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.domain.Record
import edu.nextstep.camp.calculator.domain.StringCalculator
import edu.nextstep.camp.calculator.domain.StringExpressionState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalculatorViewModel(
    val recordsRepository: RecordsRepository,
    val calculator: Calculator = StringCalculator,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    shouldShowRecords: Boolean = false,
    state: StringExpressionState = StringExpressionState.EmptyState()
) : ViewModel() {

    private val _state = MutableLiveData(state)
    val state: LiveData<StringExpressionState> get() = _state

    private val _calculationFailed = SingleLiveEvent<Boolean>()
    val calculationFailed: LiveData<Boolean>
        get() = _calculationFailed

    private val _showRecords = MutableLiveData(shouldShowRecords)
    val showRecords: LiveData<Boolean>
        get() = _showRecords

    private val _records = MutableLiveData<List<Record>>()
    val records: LiveData<List<Record>>
        get() = _records

    init {
        initRecords()
    }

    fun addOperand(operand: Int) {
        addElement(Operand(operand))
    }

    fun addElement(operator: Operator) {
        _state.value = state.value?.addElement(operator)
    }

    fun addElement(operand: Operand) {
        _state.value = state.value?.addElement(operand)
    }

    fun removeElement() {
        _state.value = state.value?.removeElement()
    }

    fun calculate() {
        val state: StringExpressionState = state.value ?: return
        runCatching {
            calculator.calculate(state)
        }
            .onSuccess {
                _state.value = StringExpressionState.of(it)
                insertRecord(Record(expression = state.toString(), result = it.value))
            }
            .onFailure {
                setCalculationFailed()
            }
    }

    fun toggleRecords() {
        _showRecords.value = showRecords.value?.not()
    }

    private fun initRecords() {
        viewModelScope.launch(dispatcher) {
            _records.postValue(recordsRepository.getAll())
        }
    }

    private fun insertRecord(record: Record) {
        viewModelScope.launch(dispatcher) {
            recordsRepository.insert(record)
        }
        _records.value = _records.value?.plus(record)
    }

    private fun setCalculationFailed() {
        _calculationFailed.value = true
    }
}
