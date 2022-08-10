package edu.nextstep.camp.calculator.domain.history

interface HistoryRepository {
    suspend fun insert(expression: String, result: Int)
    suspend fun getAll(): List<History>
}