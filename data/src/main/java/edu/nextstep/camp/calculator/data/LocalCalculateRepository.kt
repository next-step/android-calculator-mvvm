package edu.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.data.local.HistoryDao
import edu.nextstep.camp.calculator.data.mapper.HistoryMapper.toDomainData
import edu.nextstep.camp.calculator.data.mapper.HistoryMapper.toLocalData
import edu.nextstep.camp.calculator.domain.repository.CalculateRepository
import edu.nextstep.camp.calculator.domain.repository.History

internal class LocalCalculateRepository(private val historyDao: HistoryDao) : CalculateRepository {
    override suspend fun getHistoryAll(): List<History> {
        return historyDao.getAll().map { history -> history.toDomainData() }
    }

    override suspend fun save(history: History) {
        historyDao.insert(listOf(history.toLocalData()))
    }
}

