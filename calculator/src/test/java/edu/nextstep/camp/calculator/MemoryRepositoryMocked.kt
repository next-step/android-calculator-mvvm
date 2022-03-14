package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Memories
import edu.nextstep.camp.calculator.domain.Memory
import edu.nextstep.camp.calculator.domain.MemoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

internal class MemoryRepositoryMocked(
    private var memories: Memories = Memories(emptyList())
) : MemoryRepository {
    private val _state = MutableStateFlow(memories)

    override fun getMemories(): Flow<Memories> {
        return _state
    }

    override suspend fun addMemory(memory: Memory) {
        memories += memory
        _state.emit(memories)
    }
}
