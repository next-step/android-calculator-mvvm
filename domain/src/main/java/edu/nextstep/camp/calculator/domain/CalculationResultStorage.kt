package edu.nextstep.camp.calculator.domain

class CalculationResultStorage(
    private val calculationResults: List<CalculationResult> = listOf()
) {
    operator fun plus(newResult: CalculationResult): CalculationResultStorage =
        CalculationResultStorage(calculationResults + newResult)

    fun getResultsAsList() = calculationResults.toList()
}
