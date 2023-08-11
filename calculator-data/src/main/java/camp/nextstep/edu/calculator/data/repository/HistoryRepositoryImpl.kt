package camp.nextstep.edu.calculator.data.repository

import camp.nextstep.edu.calculator.data.database.HistoryDao
import camp.nextstep.edu.calculator.data.mapper.toData
import camp.nextstep.edu.calculator.data.mapper.toDomain
import camp.nextstep.edu.calculator.domain.model.History
import camp.nextstep.edu.calculator.domain.repository.HistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class HistoryRepositoryImpl(
    private val historyDao: HistoryDao
) : HistoryRepository {
    override suspend fun insertHistory(history: History) {
        withContext(Dispatchers.IO) {
            historyDao.insertHistory(history.toData())
        }
    }

    override suspend fun getHistories(): List<History> {
        var histories = emptyList<History>()
        withContext(Dispatchers.IO) {
            histories = historyDao.getHistories().map { it.toDomain() }
        }
        return histories
    }
}