package edu.nextstep.camp.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.nextstep.camp.domain.calculator.Calculator
import edu.nextstep.camp.domain.calculator.Expression
import edu.nextstep.camp.domain.calculator.Operator

class CalculatorViewModel(
    private val calculator: Calculator = Calculator(),
    private var expression: Expression = Expression.EMPTY
) : ViewModel() {

    private val _onViewState = MutableLiveData<Event<CalculatorState>>()
    val onViewState: LiveData<Event<CalculatorState>> get() = _onViewState


    fun onViewEvent(event: CalculatorEvent) {
        when (event) {
            is CalculatorEvent.AddOperand -> eventAddOperand(event.operand)
            is CalculatorEvent.AddOperator -> eventAddOperator(event.operator)
            CalculatorEvent.Calculate -> eventCalculate()
            CalculatorEvent.RemoveLast -> eventRemoveLast()
        }
    }

    private fun sendViewState(content: CalculatorState) {
        _onViewState.postValue(Event(content))
    }

    private fun eventAddOperand(operand: Int) {
        expression += operand
        sendViewState(CalculatorState.ShowExpression(expression))
    }

    private fun eventAddOperator(operator: Operator) {
        expression += operator
        sendViewState(CalculatorState.ShowExpression(expression))
    }

    private fun eventRemoveLast() {
        expression = expression.removeLast()
        sendViewState(CalculatorState.ShowExpression(expression))
    }

    private fun eventCalculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            sendViewState(CalculatorState.ShowIncompleteExpressionError)
        } else {
            expression = Expression(listOf(result))
            sendViewState(CalculatorState.ShowResult(result))
        }
    }
}
