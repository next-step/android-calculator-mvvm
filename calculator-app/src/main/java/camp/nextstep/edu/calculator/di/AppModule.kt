package camp.nextstep.edu.calculator.di

import android.content.Context
import camp.nextstep.edu.calculator.CalculatorViewModel
import camp.nextstep.edu.calculator.data.di.DataModule
import camp.nextstep.edu.calculator.domain.di.UseCaseModule

object AppModule {
    fun provideCalculatorViewModel(
        context: Context
    ): CalculatorViewModel {

        val repository = DataModule.provideHistoryRepository(context)

        val getHistoriesUseCase by lazy { UseCaseModule.provideGetHistoriesUseCase(repository) }
        val insertHistoryUseCase by lazy { UseCaseModule.provideInsertHistoryUseCase(repository) }

        return CalculatorViewModel(
            getHistoriesUseCase = getHistoriesUseCase,
            insertHistoryUseCase = insertHistoryUseCase
        )
    }
}