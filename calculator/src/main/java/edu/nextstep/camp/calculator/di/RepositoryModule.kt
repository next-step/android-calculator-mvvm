package edu.nextstep.camp.calculator.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import edu.nextstep.camp.calculator.data.repository.EvaluationRecordRepositoryImpl
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.repository.EvaluationRecordRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindEvaluationRecordStoreRepository(repository: EvaluationRecordRepositoryImpl): EvaluationRecordRepository
}

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    @ViewModelScoped
    fun provideCalculator(): Calculator = Calculator()
}
