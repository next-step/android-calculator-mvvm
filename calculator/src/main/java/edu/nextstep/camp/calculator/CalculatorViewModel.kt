package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.dodobest.domain.CalculatorRepository
import com.github.dodobest.data.ResultRecord
import com.github.dodobest.domain.Calculator
import com.github.dodobest.domain.Expression
import com.github.dodobest.domain.Operator

class CalculatorViewModel(
    private var expression: Expression,
    private val calculator: Calculator,
    private val calculatorRepository: CalculatorRepository
) : ViewModel() {
    private var _statement = MutableLiveData(expression.toString())
        val statement: LiveData<String>
            get() = _statement

    private val _calculationMemories = MutableLiveData<List<ResultRecord>>(emptyList())
        val calculationMemories: LiveData<List<ResultRecord>>
            get() = _calculationMemories

    private val _showErrorMessage = MutableLiveData<Event<Unit>>()
        val showErrorMessage: LiveData<Event<Unit>>
            get() = _showErrorMessage

    private val _isMemoryVisible = MutableLiveData(false)
        val isMemoryVisible: LiveData<Boolean>
            get() = _isMemoryVisible

    init {
        _calculationMemories.value = calculatorRepository.getMemories()
    }

    fun addToExpression(operand: Int) {
        if (isMemoryVisible.value == true) {
            return
        }
        expression += operand
        _statement.value = expression.toString()
    }

    fun addToExpression(operator: Operator) {
        if (isMemoryVisible.value == true) {
            return
        }
        expression += operator
        _statement.value = expression.toString()
    }

    fun erase() {
        if (isMemoryVisible.value == true) {
            return
        }
        expression = expression.removeLast()
        _statement.value = expression.toString()
    }

    fun equals() {
        if (isMemoryVisible.value == true || _statement.value == null) {
            return
        }

        val calcResultValue: Int? = calculator.calculate(_statement.value!!)

        if (calcResultValue == null) {
            _showErrorMessage.value = Event(Unit)
            return
        }

        val newMemory = ResultRecord(statement.value!!, "= $calcResultValue")
        calculatorRepository.addMemory(newMemory)
        _calculationMemories.value = calculatorRepository.getMemories()

        expression = Expression(listOf(calcResultValue))
        _statement.value = expression.toString()
    }

    fun memory() {
        _isMemoryVisible.value = !_isMemoryVisible.value!!
    }
}