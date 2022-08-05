package edu.nextstep.camp.calculator.data.di

import android.content.Context
import edu.nextstep.camp.calculator.data.repository.EvaluationRecordRepositoryImpl
import edu.nextstep.camp.calculator.domain.repository.EvaluationRecordRepository

object RepositoryModule {
    fun provideEvaluationRecordStoreRepository(context: Context): EvaluationRecordRepository =
        EvaluationRecordRepositoryImpl(
            dao = AppModule.provideEvaluationRecordDao(AppModule.provideCalculatorDatabase(context)),
            ioDispatcher = CoroutinesModule.providesIoDispatcher()
        )
}
