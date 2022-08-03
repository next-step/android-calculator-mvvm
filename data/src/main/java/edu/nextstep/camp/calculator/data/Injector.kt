package edu.nextstep.camp.calculator.data

import android.content.Context
import edu.nextstep.camp.calculator.domain.HistoryRepository

/**
 * Created by link.js on 2022. 08. 04..
 */
object Injector {
    fun provideSampleRepository(context: Context): HistoryRepository {
        return HistoryRepositoryImpl(AppDatabase.getDatabase(context).historyDao())
    }
}