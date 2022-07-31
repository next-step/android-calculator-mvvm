package edu.nextstep.camp.calculator

import edu.nextstep.camp.domain.calculator.Expression

sealed class CalculatorState {
    data class ShowExpression(val expression: Expression) : CalculatorState()
    data class ShowResult(val result: Int) : CalculatorState()
    object ShowIncompleteExpressionError : CalculatorState()
}
