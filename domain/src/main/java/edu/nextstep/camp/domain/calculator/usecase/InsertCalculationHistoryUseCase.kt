package edu.nextstep.camp.domain.calculator.usecase

import edu.nextstep.camp.domain.calculator.CalculationHistory
import edu.nextstep.camp.domain.calculator.CalculationHistoryRepository

class InsertCalculationHistoryUseCase(
    private val calculationHistoryRepository: CalculationHistoryRepository
) {
    suspend operator fun invoke(calculationHistory: CalculationHistory) {
        calculationHistoryRepository.insertCalculationHistory(calculationHistory)
    }
}