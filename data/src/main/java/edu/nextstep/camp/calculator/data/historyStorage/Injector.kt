package edu.nextstep.camp.calculator.data.historyStorage

import android.content.Context
import edu.nextstep.camp.calculator.domain.history.HistoryRepository

object Injector {
    fun provideHistoryRepository(context: Context): HistoryRepository {
        return HistoryRepositoryImpl(HistoryDatabase.getInstance(context).historyDao())
    }
}