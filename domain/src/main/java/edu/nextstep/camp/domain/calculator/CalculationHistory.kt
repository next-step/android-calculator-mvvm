package edu.nextstep.camp.domain.calculator

data class CalculationHistory(
    val id: Long,
    val expression: Expression,
    val result: Int
) {
    companion object {
        const val INVALID_ID = -1L
    }
}
