package com.example.domain

import com.example.domain.models.Calculator
import com.example.domain.repositories.HistoryRepository

object ModelModule {
    fun provideCalculator(
        repository: HistoryRepository
    ): Calculator = Calculator(historyRepository = repository)
}
