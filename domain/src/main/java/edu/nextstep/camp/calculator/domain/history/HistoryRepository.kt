package edu.nextstep.camp.calculator.domain.history

interface HistoryRepository {
    suspend fun insert(history: History)
    suspend fun getAll(): HistoryGroups
}