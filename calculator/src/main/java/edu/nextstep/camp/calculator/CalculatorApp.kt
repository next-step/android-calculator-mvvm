package edu.nextstep.camp.calculator

import android.app.Application
import edu.nextstep.camp.calculator.data.AppDatabase
import edu.nextstep.camp.calculator.data.RoomExpressionHistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlin.concurrent.thread

class CalculatorApp : Application() {

    lateinit var appDatabase: AppDatabase
    lateinit var expressionHistoryRepository: RoomExpressionHistoryRepository

    override fun onCreate() {
        super.onCreate()

        thread {
            appDatabase = AppDatabase.create(this)
            expressionHistoryRepository = RoomExpressionHistoryRepository(
                appDatabase.expressionHistoryDao(),
                Dispatchers.IO
            )
        }
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: CalculatorApp
    }
}