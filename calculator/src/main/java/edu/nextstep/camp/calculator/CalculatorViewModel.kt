package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.nextstep.camp.calculator.data.CalculationRecord
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.CalculatorRepository
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import kotlinx.coroutines.launch

/**
 * CalculatorActivity에 대한 viewModel
 * Created by jeongjinhong on 2022. 07. 30..
 */
class CalculatorViewModel(
    private var _expression: MutableLiveData<Expression> = MutableLiveData(Expression.EMPTY),
    private var calculatorRepository: CalculatorRepository
) : ViewModel() {

    private val calculator = Calculator()

    val expression: LiveData<Expression>
        get() = _expression

    private var _errorEvent = MutableLiveData(Event(ErrorEvent.NORMAL))
    val errorEvent: LiveData<Event<ErrorEvent>>
        get() = _errorEvent

    private var _toggleCalculationMemory = MutableLiveData(false)
    val toggleCalculationMemory: LiveData<Boolean>
        get() = _toggleCalculationMemory

    private var _updateMemory: MutableLiveData<List<CalculationRecord>> =
        MutableLiveData(listOf())
    val updateMemory: LiveData<List<CalculationRecord>>
        get() = _updateMemory

    fun addToExpression(operand: Int) {
        _expression.value = (_expression.value ?: Expression.EMPTY) + operand
    }

    fun addToExpression(operator: Operator) {
        _expression.value = (_expression.value ?: Expression.EMPTY) + operator
    }

    fun removeLast() {
        _expression.value = _expression.value?.removeLast() ?: Expression.EMPTY
    }


    fun calculate() {
        val result = calculator.calculate(expression.value.toString())
        if (result == null) {
            _errorEvent.value = Event(ErrorEvent.INCOMPLETE_EXPRESSION_ERROR)
        } else {
            storeCalculationMemory(result)
            _expression.value = Expression(listOf(result))
        }
    }

    private fun storeCalculationMemory(result: Int) {
        viewModelScope.launch {
            calculatorRepository.storeCalculationMemory(expression.value.toString(), result)
        }
    }

    fun loadCalculationMemory() {
        viewModelScope.launch {
            calculatorRepository.loadCalculationRecords()
        }
    }

    fun updateCalculationMemory() {
        _toggleCalculationMemory.value = !(_toggleCalculationMemory.value ?: false)
        _updateMemory.value = calculatorRepository.calculationRecordList as? List<CalculationRecord>
    }

    enum class ErrorEvent {
        NORMAL, INCOMPLETE_EXPRESSION_ERROR
    }

}