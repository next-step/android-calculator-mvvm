package edu.nextstep.camp.calculator.data

internal class HistoryRepositoryImpl(
    private val calculationHistoryDao: CalculationHistoryDao
) : HistoryRepository {

    override suspend fun getAllHistories(): List<CalculationHistoryEntity> {
        return calculationHistoryDao.getCalculationHistories()
    }

    override suspend fun addHistory(historyEntity: CalculationHistoryEntity): List<CalculationHistoryEntity> {
        calculationHistoryDao.insertCalculationHistory(historyEntity)
        return calculationHistoryDao.getCalculationHistories()
    }

}