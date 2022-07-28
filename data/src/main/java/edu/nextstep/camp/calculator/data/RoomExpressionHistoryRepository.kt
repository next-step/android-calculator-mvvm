package edu.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.domain.ExpressionHistoryItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RoomExpressionHistoryRepository(
    private val expressionHistoryDao: ExpressionHistoryDao,
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun setAll(expressionHistoryItem: List<ExpressionHistoryItem>) {
        withContext(ioDispatcher) {
            expressionHistoryDao.setAll(expressionHistoryItem.map { it.toEntry() })
        }
    }

    suspend fun getAll(): List<ExpressionHistoryItem> {
        return withContext(ioDispatcher) {
            expressionHistoryDao.getAll().map { it.toExpressionHistoryItem() }
        }
    }
}