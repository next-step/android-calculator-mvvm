package camp.nextstep.edu.calculator.domain.repository

import camp.nextstep.edu.calculator.domain.data.Memory

interface MemoryRepository {
    suspend fun getMemoryList(): List<Memory>
    suspend fun addMemory(memory: Memory): Boolean
}
