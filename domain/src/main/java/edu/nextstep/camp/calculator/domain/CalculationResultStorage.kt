package edu.nextstep.camp.calculator.domain

class CalculationResultStorage(
    private val calculationResults: List<CalculationResult> = listOf()
) {
    operator fun plus(newResult: CalculationResult): CalculationResultStorage {
        return CalculationResultStorage(calculationResults + newResult)
    }

    fun getResultsAsList() = calculationResults.toList()
}
