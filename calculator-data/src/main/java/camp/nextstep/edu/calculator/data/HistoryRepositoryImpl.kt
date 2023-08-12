package camp.nextstep.edu.calculator.data

import camp.nextstep.edu.calculator.domain.repo.History
import camp.nextstep.edu.calculator.domain.repo.HistoryRepository

private val HistoryEntity.domain: History
    get() = History(expression = this.expression, result = this.result.toInt())
private val History.entity: HistoryEntity
    get() = HistoryEntity(expression = this.expression, result = this.result.toString())

class HistoryRepositoryImpl(private val historyDatabase: HistoryDatabase) : HistoryRepository {
    override suspend fun addHistory(history: History) {
        historyDatabase.historyDao().insertAll(history.entity)
    }

    override suspend fun loadHistories(): List<History> {
        return historyDatabase.historyDao().getAll().map {
            it.domain
        }
    }
}
