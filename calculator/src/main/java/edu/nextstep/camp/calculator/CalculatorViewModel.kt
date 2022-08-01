package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.nextstep.camp.domain.calculator.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val calculator: Calculator = Calculator(),
    private var expression: Expression = Expression.EMPTY,
    private var isShowingHistory: Boolean = false,
    private val calculationRecordsRepository: CalculationRecordsRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _onState = MutableLiveData<Event<CalculatorState>>()
    val onState: LiveData<Event<CalculatorState>> get() = _onState

    fun onEvent(event: CalculatorEvent) {
        when (event) {
            is CalculatorEvent.AddOperand -> eventAddOperand(event.operand)
            is CalculatorEvent.AddOperator -> eventAddOperator(event.operator)
            CalculatorEvent.Calculate -> eventCalculate()
            CalculatorEvent.RemoveLast -> eventRemoveLast()
            CalculatorEvent.ToggleCalculatorHistory -> eventToggleCalculatorHistory()
        }
    }

    private fun sendViewState(content: CalculatorState) {
        _onState.postValue(Event(content))
    }

    private fun sendShowExpressionState() {
        isShowingHistory = false
        sendViewState(CalculatorState.ShowExpression(expression))
    }

    private fun sendLoadedCalculatorRecordsState() {
        viewModelScope.launch(dispatcher) {
            calculationRecordsRepository.loadCalculationRecords().let {
                CalculatorState.LoadedCalculatorHistory(it)
            }.run {
                sendViewState(this)
            }
        }
    }

    private fun eventToggleCalculatorHistory() {
        if (isShowingHistory) {
            sendShowExpressionState()
        } else {
            sendLoadedCalculatorRecordsState()
            isShowingHistory = true
        }
    }

    private fun eventAddOperand(operand: Int) {
        expression += operand
        sendShowExpressionState()
    }

    private fun eventAddOperator(operator: Operator) {
        expression += operator
        sendShowExpressionState()
    }

    private fun eventRemoveLast() {
        expression = expression.removeLast()
        sendShowExpressionState()
    }

    private fun eventCalculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            sendViewState(CalculatorState.ShowIncompleteExpressionError)
        } else {
            saveCalculatorResult(expression, result)
            expression = Expression(listOf(result))
            sendViewState(CalculatorState.ShowResult(result))
        }
    }

    private fun saveCalculatorResult(expression: Expression, result: Int) {
        viewModelScope.launch(dispatcher) {
            calculationRecordsRepository.saveCalculationRecord(
                CalculationRecord(expression, result)
            )
        }
    }
}
