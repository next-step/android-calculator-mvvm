package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.data.AppDatabase
import edu.nextstep.camp.calculator.data.ResultRecord
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Event
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorViewModel(
    private var expression: Expression,
    private var calculator: Calculator,
    private var database: AppDatabase,
    private var resultAdapter: ResultAdapter
) : ViewModel() {
    private val _result = MutableLiveData(expression.toString())
        val result: LiveData<String>
            get() = _result

    private val _showErrorMessage = MutableLiveData<Event<Unit>>()
        val showErrorMessage: LiveData<Event<Unit>>
            get() = _showErrorMessage

    fun getResultAdapter(): ResultAdapter {
        return resultAdapter
    }

    var isMemoryVisible = false

    fun addOperand(operand: Int) {
        if (isMemoryVisible) {
            return
        }
        expression += operand
        _result.value = expression.toString()
    }

    fun plus() {
        if (isMemoryVisible) {
            return
        }
        expression += Operator.Plus
        _result.value = expression.toString()
    }

    fun minus() {
        if (isMemoryVisible) {
            return
        }
        expression += Operator.Minus
        _result.value = expression.toString()
    }

    fun divide() {
        if (isMemoryVisible) {
            return
        }
        expression += Operator.Divide
        _result.value = expression.toString()
    }

    fun multiply() {
        if (isMemoryVisible) {
            return
        }
        expression += Operator.Multiply
        _result.value = expression.toString()
    }

    fun erase() {
        if (isMemoryVisible) {
            return
        }
        expression = expression.removeLast()
        _result.value = expression.toString()
    }

    fun equals() {
        if (isMemoryVisible) {
            return
        }
        val res = calculator.calculate(expression.toString())
        if (res == null) {
            _showErrorMessage.value = Event(Unit)
            return
        }

        database.resultRecordDao().insertResultRecord(ResultRecord(expression.toString(), "= $res"))

        resultAdapter.addResult(ResultRecord(expression.toString(), "= $res"))

        expression = Expression(listOf(res))
        _result.value = res.toString()
    }

    fun memory() {
        isMemoryVisible = !isMemoryVisible
    }
}