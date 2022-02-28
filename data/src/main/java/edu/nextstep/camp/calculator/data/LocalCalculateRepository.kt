package edu.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.data.local.History
import edu.nextstep.camp.calculator.data.local.HistoryDao

internal class LocalCalculateRepository(private val historyDao: HistoryDao) : CalculateRepository {
    override suspend fun save(history: History) {
        historyDao.insert(listOf(history))
    }

    override suspend fun getHistoryAll(): List<History> {
        return historyDao.getAll()
    }
}