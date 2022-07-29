package edu.nextstep.camp.calculator

import edu.nextstep.camp.domain.calculator.Operator

sealed class CalculatorViewEvent {
    companion object {
        @JvmStatic
        fun operand(number: Int) = AddOperand(number)

        @JvmStatic
        fun operator(sign: Char) = AddOperator(Operator.of(sign.toString()))
    }

    data class AddOperator(val operator: Operator) : CalculatorViewEvent()
    data class AddOperand(val operand: Int) : CalculatorViewEvent()
    object RemoveLast : CalculatorViewEvent()
    object Calculate : CalculatorViewEvent()
}
