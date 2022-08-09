package edu.nextstep.camp.calculator.domain.repository

import edu.nextstep.camp.calculator.domain.CalculateResult
import kotlinx.coroutines.flow.Flow

interface CalculateRepository {
    suspend fun insertCalculateResult(calculateResult: CalculateResult)

    suspend fun getCalculateResults(): Flow<List<CalculateResult>?>
}
