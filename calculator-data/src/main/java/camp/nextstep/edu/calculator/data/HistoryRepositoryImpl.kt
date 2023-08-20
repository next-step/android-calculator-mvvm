package camp.nextstep.edu.calculator.data

import camp.nextstep.edu.calculator.domain.repo.History
import camp.nextstep.edu.calculator.domain.repo.HistoryRepository
import javax.inject.Inject

private val HistoryEntity.domain: History
    get() = History(expression = this.expression, result = this.result.toInt())
private val History.entity: HistoryEntity
    get() = HistoryEntity(expression = this.expression, result = this.result.toString())

class HistoryRepositoryImpl @Inject constructor(
    private val historyDao: HistoryDao
) : HistoryRepository {

    override suspend fun addHistory(history: History) {
        historyDao.insertAll(history.entity)
    }

    override suspend fun loadHistories(): List<History> {
        return historyDao.getAll().map {
            it.domain
        }
    }
}
