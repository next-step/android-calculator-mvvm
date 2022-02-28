package edu.nextstep.camp.domain.calculator.repository

import edu.nextstep.camp.domain.calculator.CalculatorMemory

interface CalculatorRecordRepository {
    fun getAllRecord(): kotlinx.coroutines.flow.Flow<List<CalculatorMemory.Record>>
}
