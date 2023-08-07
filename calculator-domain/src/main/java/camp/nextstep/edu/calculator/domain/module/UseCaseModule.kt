package camp.nextstep.edu.calculator.domain.module

import camp.nextstep.edu.calculator.domain.repository.HistoryRepository
import camp.nextstep.edu.calculator.domain.usecase.DeleteHistoryUseCase
import camp.nextstep.edu.calculator.domain.usecase.GetHistoriesUseCase
import camp.nextstep.edu.calculator.domain.usecase.InsertHistoryUseCase

object UseCaseModule {
    fun provideGetHistoriesUseCase(
        repository: HistoryRepository
    ): GetHistoriesUseCase =
        GetHistoriesUseCase(repository = repository)

    fun provideInsertHistoryUseCase(
        repository: HistoryRepository
    ): InsertHistoryUseCase =
        InsertHistoryUseCase(repository = repository)

    fun provideDeleteHistoryUseCase(
        repository: HistoryRepository
    ): DeleteHistoryUseCase =
        DeleteHistoryUseCase(repository = repository)
}