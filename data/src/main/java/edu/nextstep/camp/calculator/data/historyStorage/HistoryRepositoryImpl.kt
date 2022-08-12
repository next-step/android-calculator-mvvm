package edu.nextstep.camp.calculator.data.historyStorage

import android.content.Context
import edu.nextstep.camp.calculator.domain.history.History
import edu.nextstep.camp.calculator.domain.history.HistoryGroups
import edu.nextstep.camp.calculator.domain.history.HistoryRepository

internal class HistoryRepositoryImpl(private val historyDao: HistoryDAO) : HistoryRepository {

    override suspend fun insert(history: History) {
        historyDao.insert(
            HistoryEntity(
                expression = history.expression,
                result = history.result
            )
        )
    }

    override suspend fun getAll(): HistoryGroups {
        return HistoryGroups(historyDao.getAll().map { it.toDomainModel() })
    }
}