package com.example.calculator_data

import com.example.calculator_data.database.AppDatabase
import com.example.calculator_data.database.HistoryDao

internal object DaoModule {
    fun provideHistoryDao(
        database: AppDatabase
    ): HistoryDao = database.historyDao()
}
