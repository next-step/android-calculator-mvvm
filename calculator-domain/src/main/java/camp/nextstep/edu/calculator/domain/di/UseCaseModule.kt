package camp.nextstep.edu.calculator.domain.di

import camp.nextstep.edu.calculator.domain.repository.HistoryRepository
import camp.nextstep.edu.calculator.domain.usecase.GetCalculateHistoriesUseCase
import camp.nextstep.edu.calculator.domain.usecase.PostCalculateUseCase

object UseCaseModule {
    fun provideGetCalculateHistoriesUseCase(
        repository: HistoryRepository
    ): GetCalculateHistoriesUseCase =
        GetCalculateHistoriesUseCase(repository = repository)

    fun providePostCalculateUseCase(
        repository: HistoryRepository
    ): PostCalculateUseCase =
        PostCalculateUseCase(repository = repository)
}