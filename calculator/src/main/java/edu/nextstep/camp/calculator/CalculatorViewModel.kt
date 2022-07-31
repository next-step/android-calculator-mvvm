package edu.nextstep.camp.calculator

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.memoryview.MemoryUIModel
import edu.nextstep.camp.domain.Calculator
import edu.nextstep.camp.domain.Expression
import edu.nextstep.camp.domain.Operator

class CalculatorViewModel(expression: Expression = Expression.EMPTY) : ViewModel() {
    private val calculator = Calculator()

    private var isMemoryViewDisplayed = false

    private val logs = mutableListOf<MemoryUIModel>()

    private val _visibilityMemoryView =MutableLiveData(View.GONE)
    val visibilityMemoryView: LiveData<Int>
        get() = _visibilityMemoryView

    private val _memoryLog = MutableLiveData(logs.toList())
    val memoryLog: LiveData<List<MemoryUIModel>>
        get() = _memoryLog

    private val _result = MutableLiveData(expression)
    val result: LiveData<Expression>
        get() = _result

    private val _error = SingleLiveEvent<CalculatorErrorEvent>()
    val error: LiveData<CalculatorErrorEvent>
        get() = _error

    fun addOperandToExpression(operand: Int) {
        _result.value = _result.value?.plus(operand)
    }

    fun addOperatorToExpression(operator: Operator) {
        _result.value = _result.value?.plus(operator)
    }

    fun deleteExpression() {
        _result.value = _result.value?.removeLast() ?: Expression.EMPTY
    }

    fun calculateExpression() {
        val result = calculator.calculate(_result.value?.toString() ?: Expression.EMPTY.toString())
        if (result == null) {
            _error.value = CalculatorErrorEvent.IncompleteExpressionError
            return
        }
        val completeExpression = _result.value
        _result.value = Expression(listOf(result))
        saveExpression(completeExpression.toString(), result.toString())
    }

    private fun saveExpression(expression: String, result: String) {
        logs.add(MemoryUIModel(logs.size, expression, result))
    }

    fun showMemoryView() {
        isMemoryViewDisplayed = isMemoryViewDisplayed.not()
        if (isMemoryViewDisplayed) {
            _visibilityMemoryView.value = View.VISIBLE
            _result.value = Expression.EMPTY
            _memoryLog.value = logs.toList()
        } else {
            _visibilityMemoryView.value = View.GONE
            _result.value = Expression(listOf(logs.last().resultText.toInt()))
        }
    }
}