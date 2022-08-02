package edu.nextstep.camp.calculator.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import edu.nextstep.camp.calculator.data.repository.EvaluationRecordRepositoryImpl
import edu.nextstep.camp.calculator.domain.repository.EvaluationRecordRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindEvaluationRecordStoreRepository(repository: EvaluationRecordRepositoryImpl): EvaluationRecordRepository
}
