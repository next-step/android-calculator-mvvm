package edu.nextstep.camp.calculator.domain

interface ExpressionHistoryRepository {
    suspend fun setAll(expressionHistory: List<ExpressionHistory>)
    suspend fun getAll(): List<ExpressionHistory>
}