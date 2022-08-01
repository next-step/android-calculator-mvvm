package edu.nextstep.camp.calculator

import edu.nextstep.camp.domain.calculator.Operator

sealed class CalculatorEvent {
    companion object {
        @JvmStatic
        fun operand(number: Int) = AddOperand(number)

        @JvmStatic
        fun operator(sign: Char) = AddOperator(Operator.of(sign.toString()))
    }

    data class AddOperator(val operator: Operator) : CalculatorEvent()
    data class AddOperand(val operand: Int) : CalculatorEvent()
    object RemoveLast : CalculatorEvent()
    object Calculate : CalculatorEvent()
    object ToggleCalculatorHistory : CalculatorEvent()
}
