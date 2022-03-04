package edu.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.data.local.MemoryDao
import edu.nextstep.camp.calculator.data.local.MemoryEntity
import edu.nextstep.camp.calculator.domain.Memories
import edu.nextstep.camp.calculator.domain.Memory
import edu.nextstep.camp.calculator.domain.MemoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class MemoryRepositoryImpl @Inject constructor(
    private val memoryDao: MemoryDao
) : MemoryRepository {
    override fun getMemories(): Flow<Memories> {
        return memoryDao.getAll().map { Memories(it.map(MemoryEntity::toDomain)) }
    }

    override suspend fun addMemory(memory: Memory) {
        memoryDao.insert(MemoryEntity.from(memory))
    }
}
