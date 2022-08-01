package edu.nextstep.camp.calculator.data

import android.content.Context
import edu.nextstep.camp.calculator.domain.HistoryRepository

object Injector {

    lateinit var applicationContext: Context

    private const val SHARED_PREFERENCES_NAME = "preferences"
    private val sharedPreferences by lazy {
        applicationContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    val historyRepository: HistoryRepository by lazy {
        HistoryRepositoryImpl(sharedPreferences)
    }
}
