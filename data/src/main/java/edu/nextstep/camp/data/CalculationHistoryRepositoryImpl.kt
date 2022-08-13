package edu.nextstep.camp.data

import edu.nextstep.camp.data.local.CalculationHistoryEntityDao
import edu.nextstep.camp.data.mapper.toCalculationHistory
import edu.nextstep.camp.data.mapper.toCalculationHistoryEntity
import edu.nextstep.camp.domain.calculator.CalculationHistory
import edu.nextstep.camp.domain.calculator.CalculationHistoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CalculationHistoryRepositoryImpl(
    private val calculationHistoryEntityDao: CalculationHistoryEntityDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): CalculationHistoryRepository {
    override suspend fun insertCalculationHistory(calculationHistory: CalculationHistory) {
        withContext(dispatcher) {
            calculationHistoryEntityDao.insert(calculationHistory.toCalculationHistoryEntity())
        }
    }

    override fun getAllCalculationHistory(): Flow<List<CalculationHistory>> {
        return calculationHistoryEntityDao.getAllCalculationHistoryEntity().map { calculationHistoryEntities ->
            calculationHistoryEntities.map { it.toCalculationHistory() }
        }
    }
}