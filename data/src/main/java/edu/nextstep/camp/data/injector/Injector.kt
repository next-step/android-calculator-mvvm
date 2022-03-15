package edu.nextstep.camp.data.injector

import android.content.Context
import edu.nextstep.camp.data.CalculatorDatabase
import edu.nextstep.camp.data.CalculatorRepositoryImpl
import edu.nextstep.camp.domain.CalculatorRepository

object Injector {
    fun providesCalculatorRepository(context: Context): CalculatorRepository {
        return CalculatorRepositoryImpl( calculatorDao = CalculatorDatabase.getInstance(context).calculatorDao())
    }
}