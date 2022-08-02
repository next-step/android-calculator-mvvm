package edu.nextstep.camp.calculator.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.nextstep.camp.calculator.data.CalculatorDatabase
import edu.nextstep.camp.calculator.data.EvaluationRecordDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideCalculatorDatabase(@ApplicationContext context: Context): CalculatorDatabase {
        return CalculatorDatabase.buildDatabase(context)
    }

    @Provides
    @Singleton
    fun provideEvaluationRecordDao(calculatorDatabase: CalculatorDatabase): EvaluationRecordDao {
        return calculatorDatabase.evaluationRecordDao()
    }
}
