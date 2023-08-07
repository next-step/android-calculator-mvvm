package camp.nextstep.edu.calculator

import android.app.Application
import camp.nextstep.edu.calculator.data.module.DaoModule
import camp.nextstep.edu.calculator.data.module.DatabaseModule
import camp.nextstep.edu.calculator.data.module.RepositoryModule
import camp.nextstep.edu.calculator.domain.module.UseCaseModule

class CalculatorApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: CalculatorApplication
        private val database by lazy { DatabaseModule.provideDatabase(instance) }
        private val dao by lazy { DaoModule.provideHistoryDao(database) }
        private val repository by lazy { RepositoryModule.providerHistoryRepository(dao) }

        val getHistoriesUseCase by lazy { UseCaseModule.provideGetHistoriesUseCase(repository) }
        val insertHistoryUseCase by lazy { UseCaseModule.provideInsertHistoryUseCase(repository) }
        val deleteHistoryUseCase by lazy { UseCaseModule.provideDeleteHistoryUseCase(repository) }
    }
}