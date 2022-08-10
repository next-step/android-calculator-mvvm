package edu.nextstep.camp.calculator.data

import android.content.Context
import edu.nextstep.camp.calculator.domain.CalculationResultDataBaseRepository

object ResultDataBaseInjector {
    fun provideCalculationResultDataBaseRepository(context: Context): CalculationResultDataBaseRepository {
        return CalculationResultDataBaseRepositoryImpl(CalculationResultDatabase.create(context))
    }
}