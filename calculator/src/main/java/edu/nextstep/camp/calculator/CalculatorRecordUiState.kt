package edu.nextstep.camp.calculator

import edu.nextstep.camp.domain.calculator.Expression

data class CalculatorRecordUiState(
    val expression: Expression,
    private val result: Int
) {
    val resultText get() = result.toString()
}
