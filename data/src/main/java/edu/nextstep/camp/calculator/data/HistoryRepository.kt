package edu.nextstep.camp.calculator.data

class HistoryRepository(
    private val calculationHistoryDao: CalculationHistoryDao
) {

    suspend fun getAllHistories(): List<CalculationHistoryEntity> {
        return calculationHistoryDao.getCalculationHistories()
    }

    suspend fun addHistory(historyEntity: CalculationHistoryEntity) {
        calculationHistoryDao.insertCalculationHistory(historyEntity)
    }

}