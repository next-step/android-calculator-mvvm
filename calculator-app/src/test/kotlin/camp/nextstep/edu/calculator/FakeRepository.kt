package camp.nextstep.edu.calculator

import camp.nextstep.edu.calculator.domain.CalculatorRepository
import camp.nextstep.edu.calculator.domain.Memory
import com.example.calculator.data.MemoryEntity

class FakeRepository: CalculatorRepository {
    private val memoryEntityList = arrayListOf<Memory>()
    override fun insertMemory(memory: Memory) {
        memoryEntityList.add(memory)
    }

    override fun getMemories(): List<Memory> {
        return memoryEntityList.toList()
    }
}
