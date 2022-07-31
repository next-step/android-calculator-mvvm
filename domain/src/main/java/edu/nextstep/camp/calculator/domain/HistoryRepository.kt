package edu.nextstep.camp.calculator.domain

interface HistoryRepository {

    suspend fun save(historyList: List<History>): Result<Unit>
    suspend fun fetch(): Result<List<History>>
}
