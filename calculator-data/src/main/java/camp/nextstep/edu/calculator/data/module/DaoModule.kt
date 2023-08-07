package camp.nextstep.edu.calculator.data.module

import camp.nextstep.edu.calculator.data.database.HistoryDao
import camp.nextstep.edu.calculator.data.database.HistoryDatabase

object DaoModule {
    fun provideHistoryDao(
        database: HistoryDatabase
    ): HistoryDao = database.historyDao()
}
