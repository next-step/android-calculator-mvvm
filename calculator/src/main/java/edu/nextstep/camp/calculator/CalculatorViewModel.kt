package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.CalculatorRepository
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.domain.model.Memories
import edu.nextstep.camp.calculator.domain.model.Memory

class CalculatorViewModel(
    private val calculator: Calculator,
    private val calculatorRepository: CalculatorRepository
) : ViewModel() {

    private val _displayResult = MutableLiveData<String>()
    val displayResult: LiveData<String> get() = _displayResult

    private val _showIncompleteExpressionError = SingleLiveEvent<Unit>()
    val showIncompleteExpressionError: LiveData<Unit> get() = _showIncompleteExpressionError

    private val _calculatorMode: MutableLiveData<CalculatorMode> = MutableLiveData(CalculatorMode.Expression)
    val calculatorMode: LiveData<CalculatorMode> get() = _calculatorMode

    private val _memoryResult = MutableLiveData(Memories.EMPTY)
    val memoriesResult: LiveData<Memories> get() = _memoryResult

    private var expression = Expression.EMPTY
    private var memories = getMemories()

    fun addToExpression(operand: Int) {
        expression += operand
        updateDisplayResultByExpression()
    }

    fun addToExpression(operator: Operator) {
        expression += operator
        updateDisplayResultByExpression()
    }

    fun removeLast() {
        expression = expression.removeLast()
        updateDisplayResultByExpression()
    }

    fun calculate() {
        val result = calculator.calculate(expression.toString()) ?: run {
            _showIncompleteExpressionError.value = Unit
            return
        }

        addMemory(Memory(expression.toString(), result))
        memories = getMemories()

        expression = Expression(listOf(result))

        updateDisplayResultByExpression()
    }

    private fun addMemory(memory: Memory) {
        calculatorRepository.addMemory(memory)
    }

    private fun getMemories(): Memories = calculatorRepository.getMemories()

    private fun updateDisplayResultByExpression() {
        toggleExpressionMode()
        _displayResult.value = expression.toString()
    }

    fun toggleMode() {
        when (calculatorMode.value) {
            CalculatorMode.Memory -> toggleExpressionMode()
            CalculatorMode.Expression -> toggleMemoryMode()
        }
    }

    private fun toggleExpressionMode() {
        _calculatorMode.value = CalculatorMode.Expression
    }

    private fun toggleMemoryMode() {
        _calculatorMode.value = CalculatorMode.Memory
        _memoryResult.value = memories
    }
}