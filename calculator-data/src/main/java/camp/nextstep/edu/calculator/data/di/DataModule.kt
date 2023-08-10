package camp.nextstep.edu.calculator.data.di

import android.content.Context
import camp.nextstep.edu.calculator.data.database.HistoryDatabase
import camp.nextstep.edu.calculator.data.repository.HistoryRepositoryImpl
import camp.nextstep.edu.calculator.domain.repository.HistoryRepository

object DataModule {

    private fun provideHistoryDataBase(context: Context): HistoryDatabase {
        return HistoryDatabase.getInstance(context)
    }

    fun provideHistoryRepository(
        context: Context
    ): HistoryRepository {
        return HistoryRepositoryImpl(historyDao = provideHistoryDataBase(context).historyDao())
    }
}