package edu.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.domain.History
import edu.nextstep.camp.calculator.domain.HistoryRepository

internal class HistoryRepositoryImpl(
    private val calculationHistoryDao: CalculationHistoryDao
) : HistoryRepository {

    override suspend fun getAllHistories(): List<History> {
        return calculationHistoryDao.getCalculationHistories().map { it.toDomain() }
    }

    override suspend fun addHistory(history: History): List<History> {
        calculationHistoryDao.insertCalculationHistory(history.toEntity())
        return calculationHistoryDao.getCalculationHistories().map { it.toDomain() }
    }

}