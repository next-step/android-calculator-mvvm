package edu.nextstep.camp.calculator.data.di

import android.content.Context
import edu.nextstep.camp.calculator.data.HistoryDatabase
import edu.nextstep.camp.calculator.data.HistoryRepositoryImpl
import edu.nextstep.camp.calculator.domain.HistoryRepository

object Injector {
    fun provideMemoryRepository(context: Context): HistoryRepository {
        return HistoryRepositoryImpl(HistoryDatabase.getInstance(context).historyDao())
    }
}