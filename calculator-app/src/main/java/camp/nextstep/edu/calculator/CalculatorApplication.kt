package camp.nextstep.edu.calculator

import android.app.Application
import camp.nextstep.edu.calculator.data.HistoryDatabase
import camp.nextstep.edu.calculator.data.HistoryRepositoryImpl
import camp.nextstep.edu.calculator.domain.repo.HistoryRepository

class CalculatorApplication : Application() {

    lateinit var historyRepository: HistoryRepository

    override fun onCreate() {
        super.onCreate()
        val historyDb = HistoryDatabase.getInstance(this)
        historyRepository = HistoryRepositoryImpl(historyDb)
    }
}
