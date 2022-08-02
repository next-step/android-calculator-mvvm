package edu.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.domain.History
import edu.nextstep.camp.calculator.domain.HistoryRepository

internal class HistoryRepositoryImpl(private val historyDatabase: HistoryDatabase) :
    HistoryRepository {
    override suspend fun getHistoryList(): List<History> {
        return historyDatabase.historyDao().getHistoryList().map {
            HistoryMapper().getHistoryItem(it)
        }
    }

    override suspend fun insertHistory(history: History) {
        historyDatabase.historyDao().insertHistory(HistoryMapper().getHistoryEntity(history))
    }

    companion object {
        fun getInstance(historyDatabase: HistoryDatabase): HistoryRepository =
            HistoryRepositoryImpl(historyDatabase)
    }
}