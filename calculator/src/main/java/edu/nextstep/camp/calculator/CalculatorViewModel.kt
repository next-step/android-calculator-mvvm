package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextstep.camp.calculator.data.Record
import com.nextstep.camp.calculator.data.RecordsRepository
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Operand
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.domain.StringCalculator
import edu.nextstep.camp.calculator.domain.StringExpressionState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalculatorViewModel(
    val recordsRepository: RecordsRepository,
    val calculator: Calculator = StringCalculator,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    state: StringExpressionState = StringExpressionState.EmptyState()
) : ViewModel() {

    private val _state = MutableLiveData(state)
    val state: LiveData<StringExpressionState> get() = _state

    private val _calculationFailed = SingleLiveEvent<Boolean>()
    val calculationFailed: LiveData<Boolean>
        get() = _calculationFailed

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
                insertRecord(state = state, result = it)
            }
            .onFailure {
                setCalculationFailed()
            }
    }

    private fun insertRecord(state: StringExpressionState, result: Operand) =
        viewModelScope.launch(dispatcher) {
            recordsRepository.insert(Record(expression = state.toString(), result = result.value))
        }

    private fun setCalculationFailed() {
        _calculationFailed.value = true
    }
}
