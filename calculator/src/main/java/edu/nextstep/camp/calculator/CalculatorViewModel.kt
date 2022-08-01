package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Operand
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.domain.StringCalculator
import edu.nextstep.camp.calculator.domain.StringExpressionState

class CalculatorViewModel(
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
            StringCalculator.calculate(state)
        }
            .onSuccess {
                _state.value = StringExpressionState.of(it)
            }
            .onFailure {
                setCalculationFailed()
            }
    }

    private fun setCalculationFailed() {
        _calculationFailed.value = true
    }
}
