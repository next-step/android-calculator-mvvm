package com.example.calculator_data

import com.example.calculator_data.database.HistoryDao
import com.example.calculator_data.repositories.HistoryRepositoryImpl
import com.example.domain.repositories.HistoryRepository

internal object RepositoryModule {
    fun providerHistoryRepository(historyDao: HistoryDao): HistoryRepository =
        HistoryRepositoryImpl(historyDao)
}
