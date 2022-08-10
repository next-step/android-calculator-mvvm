package edu.nextstep.camp.calculator.data

import android.content.Context
import edu.nextstep.camp.calculator.domain.CalculationResultRepository

object DataRepositoryInjector {
    fun provideCalculationResultRepository(context: Context): CalculationResultRepository {
        return CalculationResultRepositoryImpl(CalculationResultDatabase.create(context))
    }
}