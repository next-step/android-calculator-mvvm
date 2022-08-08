package edu.nextstep.camp.calculator.data

import android.content.Context

object RepositoryInjector {

    fun provideHistoryRepository(context: Context): HistoryRepository {
        return HistoryRepositoryImpl(AppDatabase.getInstance(context).calculationHistoryDao())
    }

}