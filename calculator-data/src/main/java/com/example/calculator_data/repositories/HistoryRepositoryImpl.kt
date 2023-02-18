package com.example.calculator_data.repositories

import com.example.calculator_data.database.HistoryDao
import com.example.calculator_data.database.HistoryEntity
import com.example.domain.History
import com.example.domain.repositories.HistoryRepository

class HistoryRepositoryImpl(private val dao: HistoryDao) : HistoryRepository {
    override suspend fun saveHistory(history: History) {
        dao.insert(HistoryEntity.fromHistory(history))
    }

    override suspend fun getHistories(): List<History> {
        return dao.getAll().map {
            it.toHistory()
        }
    }
}
