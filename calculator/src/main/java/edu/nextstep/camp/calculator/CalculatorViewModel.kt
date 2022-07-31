package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Operand
import edu.nextstep.camp.calculator.domain.StringExpressionState

class CalculatorViewModel : ViewModel() {

    private val _state = MutableLiveData(StringExpressionState.of(""))
    val state: LiveData<StringExpressionState> get() = _state

    private val _calculationResult = MutableLiveData<Operand>()
    val calculationResult: LiveData<Operand> get() = _calculationResult

    private val _calculationFailed = SingleLiveEvent<Boolean>()
    val calculationFailed: LiveData<Boolean>
        get() = _calculationFailed

    fun addElement(operand: Operand) {
        _state.value = state.value?.addElement(operand)
    }
}
