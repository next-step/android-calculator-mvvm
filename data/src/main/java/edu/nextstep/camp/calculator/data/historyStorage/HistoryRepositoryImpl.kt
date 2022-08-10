package edu.nextstep.camp.calculator.data.historyStorage

import android.content.Context
import edu.nextstep.camp.calculator.domain.history.History
import edu.nextstep.camp.calculator.domain.history.HistoryRepository

internal class HistoryRepositoryImpl(private val context: Context) : HistoryRepository {

    private val db = HistoryDatabase.getInstance(context)

    override suspend fun insert(expression: String, result: Int) {
        db.historyDao().insert(
            HistoryEntity(
                expression = expression,
                result = result
            )
        )
    }

    override suspend fun getAll(): List<History> {
        return db.historyDao().getAll().map { it.toDomainModel() }
    }
}