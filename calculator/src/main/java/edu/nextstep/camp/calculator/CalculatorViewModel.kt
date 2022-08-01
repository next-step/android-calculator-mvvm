package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.data.CalculationRecordItem
import edu.nextstep.camp.calculator.data.CalculatorRepository
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

/**
 * CalculatorActivity에 대한 viewModel
 * Created by jeongjinhong on 2022. 07. 30..
 */
class CalculatorViewModel(
    private var _expression: MutableLiveData<Expression> = MutableLiveData(Expression.EMPTY),
    private var calculatorRepository: CalculatorRepository = CalculatorRepository
) : ViewModel() {

    private val calculator = Calculator()

    val expression: LiveData<Expression>
        get() = _expression

    private var _errorEvent = MutableLiveData(Event(ErrorEvent.NORMAL))
    val errorEvent: LiveData<Event<ErrorEvent>>
        get() = _errorEvent

    private var _isCalculationMemoryVisible = MutableLiveData(false)
    val isCalculationMemoryVisible: LiveData<Boolean>
        get() = _isCalculationMemoryVisible

    private var _updateMemory: MutableLiveData<List<CalculationRecordItem>> =
        MutableLiveData(listOf())
    val updateMemory: LiveData<List<CalculationRecordItem>>
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
            calculatorRepository.storeCalculationMemory(expression.value.toString(), result)
            _expression.value = Expression(listOf(result))
        }
    }

    fun clickCalculationMemory() {
        _isCalculationMemoryVisible.value = !(_isCalculationMemoryVisible.value ?: false)
        _updateMemory.value = calculatorRepository.calculationRecord.calculationRecordList
    }

    enum class ErrorEvent {
        NORMAL, INCOMPLETE_EXPRESSION_ERROR
    }

}