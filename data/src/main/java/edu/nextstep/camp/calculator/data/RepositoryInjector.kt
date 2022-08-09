package edu.nextstep.camp.calculator.data

import android.content.Context
import edu.nextstep.camp.calculator.domain.HistoryRepository

object RepositoryInjector {

    fun provideHistoryRepository(context: Context): HistoryRepository {
        return HistoryRepositoryImpl(AppDatabase.getInstance(context).calculationHistoryDao())
    }

}