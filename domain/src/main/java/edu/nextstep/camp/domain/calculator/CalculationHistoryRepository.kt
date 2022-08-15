package edu.nextstep.camp.domain.calculator

import kotlinx.coroutines.flow.Flow

interface CalculationHistoryRepository {
    suspend fun insertCalculationHistory(calculationHistory: CalculationHistory)
    fun getAllCalculationHistory(): Flow<List<CalculationHistory>>
}