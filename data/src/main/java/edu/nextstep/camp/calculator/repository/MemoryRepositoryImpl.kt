package edu.nextstep.camp.calculator.repository

import edu.nextstep.camp.calculator.Memory
import edu.nextstep.camp.calculator.MemoryDao

internal class MemoryRepositoryImpl(
    private val memoryDao: MemoryDao
) : MemoryRepository {
    override fun getAllMemory() = memoryDao.getAllMemory()

    override suspend fun insert(memory: Memory) {
        memoryDao.insert(memory)
    }
}
