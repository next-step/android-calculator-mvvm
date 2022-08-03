package edu.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.domain.History
import edu.nextstep.camp.calculator.domain.HistoryRepository

/**
 * Created by link.js on 2022. 08. 04..
 */
internal class HistoryRepositoryImpl(
    private val historyDao: HistoryDao,
) : HistoryRepository {
    override suspend fun setHistories(list: List<History>) {
        historyDao.setHistories(list.map { HistoryEntity.from(it) })
    }

    override suspend fun getHistories(): List<History> {
        return historyDao.getAll().map { it.toHistory() }
    }
}