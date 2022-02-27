package edu.nextstep.camp.calculator.data

interface CalculatorRepository {
    fun addMemory(calculationMemory: CalculationMemory)

    fun getMemories(): List<CalculationMemory>
}