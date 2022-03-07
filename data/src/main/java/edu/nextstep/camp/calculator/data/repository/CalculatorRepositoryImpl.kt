package edu.nextstep.camp.calculator.data.repository

import android.content.Context
import edu.nextstep.camp.calculator.data.CalculationMemory
import edu.nextstep.camp.calculator.data.CalculatorDao
import edu.nextstep.camp.calculator.data.CalculatorDatabase

internal class CalculatorRepositoryImpl(
    private val calculatorDao: CalculatorDao
) : CalculatorRepository {
    override fun getCalculationMemoryAll(): List<CalculationMemory> {
        return calculatorDao.getCalculationMemoryAll()
    }

    override fun insertCalculationMemory(calculationMemory: CalculationMemory) {
        calculatorDao.insertCalculationMemory(calculationMemory)
    }
}

object Injector {
    fun provideSampleRepository(context: Context): CalculatorRepository {
        return CalculatorRepositoryImpl(CalculatorDatabase.getInstance(context).calculatorDao())
    }
}
