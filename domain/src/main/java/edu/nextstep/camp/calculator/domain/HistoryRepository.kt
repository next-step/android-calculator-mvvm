package edu.nextstep.camp.calculator.domain

interface HistoryRepository {

    suspend fun getAllHistories(): List<History>

    suspend fun addHistory(history: History): List<History>

}