package com.example.domain

import com.example.domain.repositories.HistoryRepository
import com.example.domain.usecases.GetHistoriesUseCase

object UseCaseModule {
    fun provideGetHistoriesUseCase(
        repository: HistoryRepository
    ): GetHistoriesUseCase = GetHistoriesUseCase(historyRepository = repository)
}
