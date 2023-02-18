package com.example.domain.repositories

import com.example.domain.models.History
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    suspend fun saveHistory(history: History): Unit
    fun getHistories(): Flow<List<History>>
}
