package edu.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.data.local.History

interface CalculateRepository {
    suspend fun getHistoryAll(): List<History>
    suspend fun save(history: History)
}