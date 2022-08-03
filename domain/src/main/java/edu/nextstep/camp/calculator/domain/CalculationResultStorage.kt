package edu.nextstep.camp.calculator.domain

class CalculationResultStorage(
    private val calculationResults: List<CalculationResult> = listOf()
) {
    operator fun plus(newResult: CalculationResult): CalculationResultStorage =
        CalculationResultStorage(calculationResults + newResult)

    operator fun plus(newResults: List<CalculationResult>): CalculationResultStorage =
        CalculationResultStorage(calculationResults + newResults)

    fun getResultsAsList() = calculationResults.toList()
}
