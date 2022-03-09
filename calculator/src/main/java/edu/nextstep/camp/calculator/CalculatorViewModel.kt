package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.nextstep.camp.calculator.data.CalculationMemory
import edu.nextstep.camp.calculator.data.repository.CalculatorRepository
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.util.Event
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val calculator: Calculator,
    private val calculatorRepository: CalculatorRepository
) : ViewModel() {
    private var _expression = MutableLiveData(Expression.EMPTY)
    val expression: LiveData<Expression> = _expression

    private val _showExpressionErrorMessage = MutableLiveData<Event<Unit>>()
    val showExpressionErrorMessage: LiveData<Event<Unit>>
        get() = _showExpressionErrorMessage

    private val _isCalculationMemories = MutableLiveData(false)
    val isCalculationMemories: LiveData<Boolean>
        get() = _isCalculationMemories

    private val _calculationMemories = MutableLiveData<List<CalculationMemory>>(emptyList())
    val calculationMemories: LiveData<List<CalculationMemory>>
        get() = _calculationMemories

    init {
        viewModelScope.launch {
            _calculationMemories.postValue(calculatorRepository.getCalculationMemoryAll())
        }
    }

    fun addToExpression(operand: Int) {
        hideMemory()
        _expression.value = _expression.value?.plus(operand)
    }

    fun addToExpression(operator: Operator) {
        hideMemory()
        _expression.value = _expression.value?.plus(operator)
    }

    fun removeLast() {
        hideMemory()
        _expression.value = expression.value?.removeLast()
    }

    fun calculate() {
        val expression = _expression.value ?: return
        val rawExpression = expression.toString()
        val result = calculator.calculate(rawExpression)
        if (result == null) {
            _showExpressionErrorMessage.value = Event(Unit)
            return
        }
        val newMemories = CalculationMemory(expression.toString(), result.toString())
        viewModelScope.launch {
            calculatorRepository.insertCalculationMemory(newMemories)
            _calculationMemories.postValue(calculatorRepository.getCalculationMemoryAll())
        }

        _expression.value = Expression(listOf(result))
    }

    fun toggleCalculationMemories() {
        val isVisible = _isCalculationMemories.value ?: false
        _isCalculationMemories.value = !isVisible
    }

    fun hideMemory() {
        _isCalculationMemories.value = false
    }
}