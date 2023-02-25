package com.example.calculator_data

import android.content.Context
import com.example.domain.repositories.HistoryRepository

object Injector {
    fun provideRepository(context: Context, isInMemory: Boolean): HistoryRepository {
        val db = if (isInMemory) {
            DatabaseModule.providerInMemoryDatabase(context)
        } else {
            DatabaseModule.provideDatabase(context)
        }

        val dao = DaoModule.provideHistoryDao(db)

        return RepositoryModule.providerHistoryRepository(dao)
    }
}
