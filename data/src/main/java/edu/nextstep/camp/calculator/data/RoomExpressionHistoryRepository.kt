package edu.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.domain.ExpressionHistories
import edu.nextstep.camp.calculator.domain.ExpressionHistoryRepository

internal class RoomExpressionHistoryRepository(
    private val expressionHistoryDao: ExpressionHistoryDao
) : ExpressionHistoryRepository {
    override suspend fun setAll(histories: ExpressionHistories) {
        expressionHistoryDao.setAll(histories.histories.map { it.toEntity() })
    }

    override suspend fun getAll(): ExpressionHistories {
        return expressionHistoryDao.getAll()
            .map { it.toExpressionHistoryItem() }
            .let { ExpressionHistories(it) }
    }
}