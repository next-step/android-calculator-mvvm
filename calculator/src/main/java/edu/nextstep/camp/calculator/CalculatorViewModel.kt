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

    private val _onViewState = MutableLiveData<Event<CalculatorViewState>>()
    val onViewState: LiveData<Event<CalculatorViewState>> get() = _onViewState


    fun onViewEvent(event: CalculatorViewEvent) {
        when (event) {
            is CalculatorViewEvent.AddOperand -> eventAddOperand(event.operand)
            is CalculatorViewEvent.AddOperator -> eventAddOperator(event.operator)
            CalculatorViewEvent.Calculate -> eventCalculate()
            CalculatorViewEvent.RemoveLast -> eventRemoveLast()
        }
    }

    private fun sendViewState(content: CalculatorViewState) {
        _onViewState.postValue(Event(content))
    }

    private fun eventAddOperand(operand: Int) {
        expression += operand
        sendViewState(CalculatorViewState.ShowExpression(expression))
    }

    private fun eventAddOperator(operator: Operator) {
        expression += operator
        sendViewState(CalculatorViewState.ShowExpression(expression))
    }

    private fun eventRemoveLast() {
        expression = expression.removeLast()
        sendViewState(CalculatorViewState.ShowExpression(expression))
    }

    private fun eventCalculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            sendViewState(CalculatorViewState.ShowIncompleteExpressionError)
        } else {
            expression = Expression(listOf(result))
            sendViewState(CalculatorViewState.ShowResult(result))
        }
    }
}
