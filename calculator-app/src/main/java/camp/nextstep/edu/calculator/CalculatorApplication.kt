package camp.nextstep.edu.calculator

import android.app.Application
import com.example.calculator_data.DaoModule
import com.example.calculator_data.DatabaseModule
import com.example.calculator_data.RepositoryModule
import com.example.domain.ModelModule
import com.example.domain.UseCaseModule

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
        val calculator by lazy { ModelModule.provideCalculator(repository) }
        val getHistoriesUseCase by lazy { UseCaseModule.provideGetHistoriesUseCase(repository) }

    }
}
