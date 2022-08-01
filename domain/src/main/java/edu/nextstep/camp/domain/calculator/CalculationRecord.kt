package edu.nextstep.camp.domain.calculator

data class CalculationRecord(
    val expression: Expression,
    val result: Int
) {
    override fun toString(): String {
        return "$expression\n= $result\n"
    }
}