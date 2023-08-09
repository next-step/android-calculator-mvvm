package camp.nextstep.edu.calculator.data.repository

import camp.nextstep.edu.calculator.data.database.HistoryDao
import camp.nextstep.edu.calculator.data.mapper.toData
import camp.nextstep.edu.calculator.data.mapper.toDomain
import camp.nextstep.edu.calculator.domain.model.History
import camp.nextstep.edu.calculator.domain.repository.HistoryRepository
import java.util.concurrent.ExecutorService

internal class HistoryRepositoryImpl(
    private val historyDao: HistoryDao,
    private val executorService: ExecutorService
) : HistoryRepository {
    override fun insertHistory(history: History) {
        executorService.submit {
            historyDao.insertHistory(history.toData())
        }
    }

    override fun getHistories(): List<History> {
        return executorService.submit<List<History>> {
            historyDao.getHistories().map { it.toDomain() }
        }.get()
    }
}