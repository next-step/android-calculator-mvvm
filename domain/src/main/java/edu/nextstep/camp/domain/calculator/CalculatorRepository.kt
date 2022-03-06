package edu.nextstep.camp.domain.calculator

import edu.nextstep.camp.domain.calculator.model.CalculatorRecord
import kotlinx.coroutines.flow.Flow

interface CalculatorRepository {
    fun getAllRecord(): Flow<List<CalculatorRecord>>
    suspend fun addRecord(record: CalculatorRecord)
}
