package com.example.calculator_data.repositories

import com.example.calculator_data.database.HistoryDao
import com.example.calculator_data.database.HistoryEntity
import com.example.domain.models.History
import com.example.domain.repositories.HistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HistoryRepositoryImpl(private val dao: HistoryDao) : HistoryRepository {
    override suspend fun saveHistory(history: History) {
        dao.insert(HistoryEntity.fromHistory(history))
    }

    override fun getHistories(): Flow<List<History>> {
        return dao.getAll().map { historyEntities ->
            historyEntities.map {
                it.toHistory()
            }
        }
    }
}
