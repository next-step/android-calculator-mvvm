package edu.nextstep.camp.calculator.domain

interface ExpressionHistoryRepository {
    suspend fun setAll(expressionHistoryItem: List<ExpressionHistoryItem>)
    suspend fun getAll(): List<ExpressionHistoryItem>
}