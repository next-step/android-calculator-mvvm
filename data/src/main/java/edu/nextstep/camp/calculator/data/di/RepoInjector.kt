package edu.nextstep.camp.calculator.data.di

import android.content.Context
import edu.nextstep.camp.calculator.data.CalculatorRepositoryImpl
import edu.nextstep.camp.calculator.data.local.CalculatorDatabase
import edu.nextstep.camp.calculator.domain.CalculatorRepository

object RepoInjector {
    fun provideCalculatorRepository(context: Context): CalculatorRepository {
        val database = CalculatorDatabase.getDatabase(context)
        return CalculatorRepositoryImpl.getInstance(database.calculatorDao())
    }
}
