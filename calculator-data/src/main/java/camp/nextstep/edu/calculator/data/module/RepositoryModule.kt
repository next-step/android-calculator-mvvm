package camp.nextstep.edu.calculator.data.module

import camp.nextstep.edu.calculator.data.database.HistoryDao
import camp.nextstep.edu.calculator.data.repository.HistoryRepositoryImpl
import camp.nextstep.edu.calculator.domain.repository.HistoryRepository

object RepositoryModule {

    fun providerHistoryRepository(
        historyDao: HistoryDao
    ): HistoryRepository =
        HistoryRepositoryImpl(historyDao)
}