package camp.nextstep.edu.calculator.domain

interface CalculatorRepositoryInterface {
    suspend fun getCalculatorResults(): List<CalculationResult>
    suspend fun insertCalculatorResult(calculationResult: CalculationResult)
}