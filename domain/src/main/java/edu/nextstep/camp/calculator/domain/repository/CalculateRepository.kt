package edu.nextstep.camp.calculator.domain.repository

interface CalculateRepository {
    suspend fun getHistoryAll(): List<History>
    suspend fun save(history: History)
}