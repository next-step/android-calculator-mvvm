package camp.nextstep.edu.calculator.domain

interface CalculatorRepository {
    fun insertMemory(memory: Memory)

    fun getMemories(): List<Memory>
}
