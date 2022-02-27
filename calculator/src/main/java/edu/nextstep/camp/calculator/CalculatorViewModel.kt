package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.data.CalculationMemory
import edu.nextstep.camp.calculator.data.CalculatorRepository
import edu.nextstep.camp.calculator.data.CalculatorRepositoryImpl
import edu.nextstep.camp.calculator.domain.*
import edu.nextstep.camp.calculator.util.Event

class CalculatorViewModel(
    private val calculator: Calculator = Calculator(),
    private val calculatorRepository: CalculatorRepository = CalculatorRepositoryImpl()
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
        _calculationMemories.value = calculatorRepository.getMemories()
    }

    fun addToExpression(operand: Int) {
        _expression.value = _expression.value?.plus(operand)
    }

    fun addToExpression(operator: Operator) {
        _expression.value = _expression.value?.plus(operator)
    }

    fun removeLast() {
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

        val newMemories = CalculationMemory(expression, result)
        calculatorRepository.addMemory(newMemories)
        _calculationMemories.value = calculatorRepository.getMemories()

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