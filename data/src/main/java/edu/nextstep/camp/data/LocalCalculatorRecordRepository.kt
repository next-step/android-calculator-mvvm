package edu.nextstep.camp.data

import edu.nextstep.camp.domain.calculator.CalculatorMemory
import edu.nextstep.camp.domain.calculator.repository.CalculatorRecordRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class LocalCalculatorRecordRepository private constructor(
    private val calculatorRecordDAO: CalculatorRecordDAO,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : CalculatorRecordRepository {
    override fun getAllRecord(): Flow<List<CalculatorMemory.Record>> =
        calculatorRecordDAO.getAllRecord()
            .map { it.toListOfDomain() }
            .flowOn(ioDispatcher)
}
