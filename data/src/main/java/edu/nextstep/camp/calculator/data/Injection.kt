package edu.nextstep.camp.calculator.data

import android.content.Context
import edu.nextstep.camp.calculator.domain.HistoryRepository


object Injection {
    fun historyRepository(context: Context): HistoryRepository {
        return HistoryRepositoryImpl.getInstance(HistoryDatabase.getInstance(context))
    }
}