package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Memory
import camp.nextstep.edu.calculator.domain.Operator
import com.example.calculator.data.CalculatorDao
import com.example.calculator.data.CalculatorDatabase
import com.example.calculator.data.MemoryEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class CalculatorViewModel(initFormula: List<Any> = emptyList(), private val calculatorDao: CalculatorDao) : ViewModel() {
    private val calculator = Calculator()

    private var _formula = MutableLiveData(Expression(initFormula))
    val formula: LiveData<Expression>
        get() = _formula

    private var _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int>
        get() = _toastEvent

    private var _memoryList = MutableLiveData<List<Memory>>()
    val memoryList: LiveData<List<Memory>>
        get() = _memoryList

    private var _showHistoryEvent = SingleLiveEvent<Boolean>()
    val showHistory: LiveData<Boolean>
        get() = _showHistoryEvent

    fun addToExpression(operand: Int) {
        _formula.value = _formula.value?.plus(operand)
    }

    fun addToExpression(operator: Operator) {
        _formula.value = _formula.value?.plus(operator)
    }

    fun removeLast() {
        _formula.value = _formula.value?.removeLast()
    }

    fun calculate() {
        val result = calculator.calculate(_formula.value.toString())
        if (result == null) {
            _toastEvent.value = R.string.incomplete_expression
        } else {
            val formula = _formula.value.toString()
            _formula.value = Expression(listOf(result))

            CoroutineScope(Dispatchers.IO).launch {
                calculatorDao.insertMemory(
                    MemoryEntity(
                        expression = formula,
                        result = result.toString()
                    )
                )
            }
        }
    }

    fun getHistory() {
        if(_showHistoryEvent.value == null) {
            _showHistoryEvent.value = false
        }

        if(_showHistoryEvent.value!!) {
            _showHistoryEvent.value = false
        } else {
            CoroutineScope(Dispatchers.IO).launch {
                val memoryList = arrayListOf<Memory>()
                for (memory in calculatorDao.getMemories()) {
                    memoryList.add(
                        Memory(
                            index = memory.id,
                            expression = memory.expression,
                            result = memory.result
                        )
                    )
                }
                _memoryList.postValue(memoryList)
                _showHistoryEvent.postValue(true)
            }
        }
    }
}
