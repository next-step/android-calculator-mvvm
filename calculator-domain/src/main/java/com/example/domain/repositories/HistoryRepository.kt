package com.example.domain.repositories

import com.example.domain.models.History

interface HistoryRepository {
    suspend fun saveHistory(history: History): Unit
    suspend fun getHistories(): List<History>
}
