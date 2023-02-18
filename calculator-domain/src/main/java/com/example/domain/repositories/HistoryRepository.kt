package com.example.domain.repositories

import com.example.domain.History

interface HistoryRepository {
    suspend fun saveHistory(history: History): Unit
    suspend fun getHistories(): List<History>
}
