package com.example.domain.usecases

import com.example.domain.models.History
import com.example.domain.repositories.HistoryRepository
import kotlinx.coroutines.flow.Flow

class GetHistoriesUseCase(private val historyRepository: HistoryRepository) {
    operator fun invoke(): Flow<List<History>> {
        return historyRepository.getHistories()
    }
}
