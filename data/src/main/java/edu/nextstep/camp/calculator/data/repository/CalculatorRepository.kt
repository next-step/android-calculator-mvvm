package edu.nextstep.camp.calculator.data.repository

import edu.nextstep.camp.calculator.data.CalculationMemory

interface CalculatorRepository {

    fun getCalculationMemoryAll(): List<CalculationMemory>

    fun insertCalculationMemory(calculationMemory: CalculationMemory)
}