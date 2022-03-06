package edu.nextstep.camp.data.di

import android.content.Context
import edu.nextstep.camp.data.LocalCalculatorRepository
import edu.nextstep.camp.data.db.CalculatorDatabase
import edu.nextstep.camp.domain.calculator.CalculatorRepository

object RepositoryInjector {
    fun provideCalculatorRepository(context: Context): CalculatorRepository {
        val database = CalculatorDatabase.getInstance(context)
        return LocalCalculatorRepository.getInstance(database.calculatorRecordDao())
    }
}
