package edu.nextstep.camp.calculator

import android.app.Application
import edu.nextstep.camp.calculator.data.Injector
import edu.nextstep.camp.calculator.domain.ExpressionHistoryRepository
import kotlin.concurrent.thread

class CalculatorApp : Application() {

    lateinit var expressionHistoryRepository: ExpressionHistoryRepository

    override fun onCreate() {
        super.onCreate()

        thread {
            expressionHistoryRepository = Injector.provideExpressionHistoryRepository(this)
        }
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: CalculatorApp
    }
}