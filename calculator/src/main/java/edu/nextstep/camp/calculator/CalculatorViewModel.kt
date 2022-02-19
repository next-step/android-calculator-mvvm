package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.calculator.data.AppDatabase
import edu.nextstep.camp.calculator.data.Memory
import edu.nextstep.camp.calculator.data.ResultRecord
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Event
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.exp

class CalculatorViewModel(
    private var expression: Expression,
    private var calculator: Calculator,
    private var database: AppDatabase,
    private var memory: Memory
) : ViewModel() {
    private val _result = MutableLiveData(expression.toString())
        val result: LiveData<String>
            get() = _result

    private val _showErrorMessage = MutableLiveData<Event<Unit>>()
    val showErrorMessage: LiveData<Event<Unit>>
        get() = _showErrorMessage

    fun operandClick(operand: Int) {
        if (memory.isMemoryShow) {
            return
        }
        expression += operand
        _result.value = expression.toString()
    }

    fun plus() {
        if (memory.isMemoryShow) {
            return
        }
        expression += Operator.Plus
        _result.value = expression.toString()
    }

    fun minus() {
        if (memory.isMemoryShow) {
            return
        }
        expression += Operator.Minus
        _result.value = expression.toString()
    }

    fun divide() {
        if (memory.isMemoryShow) {
            return
        }
        expression += Operator.Divide
        _result.value = expression.toString()
    }

    fun multiply() {
        if (memory.isMemoryShow) {
            return
        }
        expression += Operator.Multiply
        _result.value = expression.toString()
    }

    fun erase() {
        if (memory.isMemoryShow) {
            return
        }
        expression = expression.removeLast()
        _result.value = expression.toString()
    }

    fun equals() {
        if (memory.isMemoryShow) {
            return
        }
        val res = calculator.calculate(expression.toString())
        if (res == null) {
            _showErrorMessage.value = Event(Unit)
            return
        }

        database.resultRecordDao().insertResultRecord(ResultRecord(expression.toString(), res.toString()))

        memory.addResult(ResultRecord(expression.toString(), res.toString()))

        expression = Expression(listOf(res))
        _result.value = res.toString()
    }

    fun memory() {
        if (memory.isMemoryShow) {
            _result.value = expression.toString()
            memory.isMemoryShow = false
            return
        }

        var string = ""

        for (resultRecord in memory.getResult()) {
            string += resultRecord.expression + "\n= " + resultRecord.result + "\n\n"
        }

        _result.value = string
        memory.isMemoryShow = true
    }
}