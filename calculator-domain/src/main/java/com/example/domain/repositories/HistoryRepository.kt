package com.example.domain.repositories

import com.example.domain.History

interface HistoryRepository {
    fun saveHistory(history: History): Unit
    fun getHistories(): List<History>
}
