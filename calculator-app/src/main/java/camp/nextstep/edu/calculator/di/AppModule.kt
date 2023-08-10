package camp.nextstep.edu.calculator.di

import android.content.Context
import camp.nextstep.edu.calculator.CalculatorViewModel
import camp.nextstep.edu.calculator.data.di.DataModule
import camp.nextstep.edu.calculator.domain.di.UseCaseModule
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object AppModule {
    fun provideCalculatorViewModel(
        context: Context
    ): CalculatorViewModel {

        val repository = DataModule.provideHistoryRepository(context = context)

        val getCalculateHistoriesUseCase by lazy { UseCaseModule.provideGetCalculateHistoriesUseCase(repository) }
        val postCalculateUseCase by lazy { UseCaseModule.providePostCalculateUseCase(repository) }

        return CalculatorViewModel(
            getCalculateHistoriesUseCase = getCalculateHistoriesUseCase,
            postCalculateUseCase = postCalculateUseCase
        )
    }
}