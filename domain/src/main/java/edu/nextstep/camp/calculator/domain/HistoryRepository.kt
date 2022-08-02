package edu.nextstep.camp.calculator.domain

interface HistoryRepository {
    suspend fun getHistoryList(): List<History>
    suspend fun insertHistory(history: History)
}