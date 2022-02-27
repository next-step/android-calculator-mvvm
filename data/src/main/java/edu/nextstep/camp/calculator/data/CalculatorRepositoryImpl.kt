package edu.nextstep.camp.calculator.data

class CalculatorRepositoryImpl: CalculatorRepository {
    private val memories = mutableListOf<CalculationMemory>()

    override fun addMemory(calculationMemory: CalculationMemory) {
        memories.add(calculationMemory)
    }

    override fun getMemories(): List<CalculationMemory> {
        return memories.toList()
    }
}