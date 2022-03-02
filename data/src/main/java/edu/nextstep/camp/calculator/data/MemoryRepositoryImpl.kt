package edu.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.data.local.MemoryDao
import edu.nextstep.camp.calculator.data.local.MemoryEntity
import edu.nextstep.camp.calculator.domain.Memory
import edu.nextstep.camp.calculator.domain.MemoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class MemoryRepositoryImpl(
    private val memoryDao: MemoryDao
) : MemoryRepository {
    override fun getAllMemory(): Flow<List<Memory>> {
        return memoryDao.getAll().map { it.map(MemoryEntity::toDomain) }
    }

    override suspend fun addMemory(memory: Memory) {
        memoryDao.insert(MemoryEntity.from(memory))
    }
}
