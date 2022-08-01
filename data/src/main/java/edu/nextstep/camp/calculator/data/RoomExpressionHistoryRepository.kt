package edu.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.domain.ExpressionHistory
import edu.nextstep.camp.calculator.domain.ExpressionHistoryRepository

class RoomExpressionHistoryRepository(
    private val expressionHistoryDao: ExpressionHistoryDao
) : ExpressionHistoryRepository {
    override suspend fun setAll(expressionHistory: List<ExpressionHistory>) {
        expressionHistoryDao.setAll(expressionHistory.map { it.toEntity() })
    }

    override suspend fun getAll(): List<ExpressionHistory> {
        return expressionHistoryDao.getAll().map { it.toExpressionHistoryItem() }
    }
}