package edu.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.domain.ExpressionHistory
import edu.nextstep.camp.calculator.domain.ExpressionHistoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RoomExpressionHistoryRepository(
    private val expressionHistoryDao: ExpressionHistoryDao,
    private val ioDispatcher: CoroutineDispatcher
) : ExpressionHistoryRepository {
    override suspend fun setAll(expressionHistory: List<ExpressionHistory>) {
        withContext(ioDispatcher) {
            expressionHistoryDao.setAll(expressionHistory.map { it.toEntry() })
        }
    }

    override suspend fun getAll(): List<ExpressionHistory> {
        return withContext(ioDispatcher) {
            expressionHistoryDao.getAll().map { it.toExpressionHistoryItem() }
        }
    }
}