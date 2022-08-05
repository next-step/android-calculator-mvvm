package edu.nextstep.camp.calculator.domain

interface ExpressionHistoryRepository {
    suspend fun setAll(expressionHistory: ExpressionHistories)
    suspend fun getAll(): ExpressionHistories
}