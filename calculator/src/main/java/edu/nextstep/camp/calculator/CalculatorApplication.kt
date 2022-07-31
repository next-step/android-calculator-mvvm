package edu.nextstep.camp.calculator

import android.app.Application
import edu.nextstep.camp.calculator.data.AppDatabase
import edu.nextstep.camp.calculator.data.HistoryRepository

/**
 * Created by link.js on 2022. 07. 31..
 */
class CalculatorApplication : Application() {
    private lateinit var database : AppDatabase
    lateinit var repository: HistoryRepository

    override fun onCreate() {
        super.onCreate()
        database = AppDatabase.getDatabase(this)
        repository = HistoryRepository(database.historyDao())
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: CalculatorApplication
    }
}