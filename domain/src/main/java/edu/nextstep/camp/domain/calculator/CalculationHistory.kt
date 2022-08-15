package edu.nextstep.camp.domain.calculator

data class CalculationHistory(
    val id: Long,
    val expressionText: String,
    val result: Int
) {
    companion object {
        const val DEFAULT_ID = -1L
    }
}
