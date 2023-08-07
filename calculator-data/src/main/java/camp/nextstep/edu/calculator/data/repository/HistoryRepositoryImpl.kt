package camp.nextstep.edu.calculator.data.repository

import camp.nextstep.edu.calculator.data.database.HistoryDao
import camp.nextstep.edu.calculator.data.mapper.toData
import camp.nextstep.edu.calculator.data.mapper.toDomain
import camp.nextstep.edu.calculator.domain.model.History
import camp.nextstep.edu.calculator.domain.repository.HistoryRepository

class HistoryRepositoryImpl(
    private val historyDao: HistoryDao
) : HistoryRepository {
    override suspend fun insertHistory(history: History) {
        historyDao.insertHistory(history.toData())
    }

    override suspend fun getHistories(): List<History> {
        return historyDao.getHistories().map { it.toDomain() }
    }
}