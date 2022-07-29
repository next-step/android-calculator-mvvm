package edu.nextstep.camp.calculator

import edu.nextstep.camp.domain.calculator.Expression

sealed class CalculatorViewState {
    data class ShowExpression(val expression: Expression) : CalculatorViewState()
    data class ShowResult(val result: Int) : CalculatorViewState()
    object ShowIncompleteExpressionError : CalculatorViewState()
}
