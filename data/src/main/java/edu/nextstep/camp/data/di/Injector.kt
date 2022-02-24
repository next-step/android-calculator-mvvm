package edu.nextstep.camp.data.di

import android.content.Context
import edu.nextstep.camp.calculator.domain.CalculatorRepository
import edu.nextstep.camp.data.CalculatorDefaultRepository
import edu.nextstep.camp.data.database.CalculatorDatabase
import java.util.concurrent.Executors

object Injector {

    private fun provideCalculatorDatabase(context: Context): CalculatorDatabase = CalculatorDatabase.create(context)

    fun provideCalculatorRepository(context: Context): CalculatorRepository {
        val database = provideCalculatorDatabase(context)
        val executors = Executors.newSingleThreadExecutor()

        return CalculatorDefaultRepository(database.calculatorDao(), executors)
    }
}