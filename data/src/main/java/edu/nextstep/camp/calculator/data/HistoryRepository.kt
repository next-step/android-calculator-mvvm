package edu.nextstep.camp.calculator.data

interface HistoryRepository {

    suspend fun getAllHistories(): List<CalculationHistoryEntity>

    suspend fun addHistory(historyEntity: CalculationHistoryEntity): List<CalculationHistoryEntity>

}