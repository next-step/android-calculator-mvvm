package edu.nextstep.camp.domain.calculator.usecase

import edu.nextstep.camp.domain.calculator.CalculationHistory
import edu.nextstep.camp.domain.calculator.CalculationHistoryRepository
import kotlinx.coroutines.flow.Flow

class GetAllCalculationHistoryUseCase(
    private val calculationHistoryRepository: CalculationHistoryRepository
) {
    operator fun invoke(): Flow<List<CalculationHistory>> {
        return calculationHistoryRepository.getAllCalculationHistory()
    }
}