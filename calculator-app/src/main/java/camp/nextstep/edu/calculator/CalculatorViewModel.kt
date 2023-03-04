package camp.nextstep.edu.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import camp.nextstep.edu.calculator.domain.Calculator
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator
import camp.nextstep.edu.calculator.domain.model.Record
import camp.nextstep.edu.calculator.domain.repository.CalculatorRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val calculatorRepository: CalculatorRepository
) : ViewModel() {
    private var _textInTextView = MutableLiveData<String>()
    val textInTextView: LiveData<String>
        get() = _textInTextView

    private var _expression = MutableLiveData(Expression.EMPTY)
    val expression: LiveData<Expression>
        get() = _expression

    private var _result = MutableLiveData<Int>()
    val result: LiveData<Int>
        get() = _result

    private var _onCalculationErrorEvent = SingleLiveEvent<Unit>()
    val onCalculationErrorEvent
        get() = _onCalculationErrorEvent

    private val calculator = Calculator()

    private var _savedRecords = MutableStateFlow<List<Record>>(listOf())
    val savedRecords: StateFlow<List<Record>>
        get() = _savedRecords

    private var _showMemoryMode = MutableLiveData<Boolean>(false)
    val showMemoryMode: LiveData<Boolean>
        get() = _showMemoryMode

    private var _prevExpression = MutableLiveData<String>()

    fun addToExpression(operand: Int) {
        _expression.value = _expression.value?.plus(operand)
        _textInTextView.value = _expression.value.toString()
    }

    fun addToExpression(operator: Operator) {
        _expression.value = _expression.value?.plus(operator)
        _textInTextView.value = _expression.value.toString()
    }

    fun removeLast() {
        _expression.value = _expression.value?.removeLast()
        _textInTextView.value = _expression.value.toString()
    }

    fun calculate() {
        val calculateResult = calculator.calculate(_expression.value.toString())

        if (calculateResult == null) {
            _onCalculationErrorEvent.value = Unit
        } else {
            _result.value = calculateResult!!
            _textInTextView.value = _result.value.toString()
            insertRecord()
        }
    }

    private fun insertRecord() {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                calculatorRepository.insertRecord(
                    Record(
                        expression.value.toString(),
                        result.value ?: 0
                    )
                )
            }
        }
    }

    fun showAllRecords() {
        setTextView()

        viewModelScope.launch {
            calculatorRepository.getAllRecords().stateIn(
                viewModelScope,
                SharingStarted.Lazily,
                emptyList()
            ).collect {
                _savedRecords.emit(it)
            }
        }
        changeMemoryMode()
    }

    fun showPrevExpression() {
        _textInTextView.value = _prevExpression.value
        changeMemoryMode()
    }

    private fun setTextView() {
        _prevExpression.value = _textInTextView.value
        _textInTextView.value = ""
    }

    private fun changeMemoryMode() {
        _showMemoryMode.value = _showMemoryMode.value != true
    }
}