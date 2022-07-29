package edu.nextstep.camp.calculator

sealed class CalculatorErrorEvent {
    object IncompleteExpressionError: CalculatorErrorEvent()
}
