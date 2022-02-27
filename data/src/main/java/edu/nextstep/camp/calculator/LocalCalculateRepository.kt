package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.local.History
import edu.nextstep.camp.calculator.local.HistoryDao

internal class LocalCalculateRepository(private val historyDao: HistoryDao) : CalculateRepository {
    override val historyAll: List<History>
        get() = historyDao.getAll()

    override fun save(history: History) {
        historyDao.insert(listOf(history))
    }
}