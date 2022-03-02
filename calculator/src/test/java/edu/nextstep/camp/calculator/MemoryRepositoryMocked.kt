package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Memories
import edu.nextstep.camp.calculator.domain.Memory
import edu.nextstep.camp.calculator.domain.MemoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class MemoryRepositoryMocked(
    private var memories: Memories = Memories()
) : MemoryRepository {
    override fun getAllMemory(): Flow<List<Memory>> {
        return flowOf(memories.toList())
    }

    override suspend fun addMemory(memory: Memory) {
        memories += memory
    }
}
