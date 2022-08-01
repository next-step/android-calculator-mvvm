package edu.nextstep.camp.calculator

import android.app.Application
import edu.nextstep.camp.calculator.domain.ExpressionHistoryRepository
import kotlin.concurrent.thread

class CalculatorApp : Application() {

    lateinit var expressionHistoryRepository: ExpressionHistoryRepository

    override fun onCreate() {
        super.onCreate()

        thread {
            // TODO sinseungmin 2022/08/02: 주입받아야함.
//            appDatabase = AppDatabase.create(this)
//            expressionHistoryRepository = RoomExpressionHistoryRepository(
//                appDatabase.expressionHistoryDao()
//            )
        }
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: CalculatorApp
    }
}