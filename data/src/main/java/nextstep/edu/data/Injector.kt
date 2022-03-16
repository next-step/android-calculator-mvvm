package nextstep.edu.data

import android.content.Context

object Injector {
    fun provideHistoryRepository(context: Context): CalculationHistoryRepository {
        return CalculationHistoryImpl(
            historyDao = HistoryDatabase.getInstance(context).historyDao()
        )
    }
}