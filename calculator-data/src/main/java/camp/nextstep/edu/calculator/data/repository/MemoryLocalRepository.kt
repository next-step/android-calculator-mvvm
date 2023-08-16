package camp.nextstep.edu.calculator.data.repository

import camp.nextstep.edu.calculator.data.MemoryEntity
import camp.nextstep.edu.calculator.data.MemoryDao
import camp.nextstep.edu.calculator.data.MemoryDatabase
import camp.nextstep.edu.calculator.domain.data.Memory
import camp.nextstep.edu.calculator.domain.repository.MemoryRepository
import android.content.Context

internal class MemoryLocalRepository(
    context: Context,
    memoryDatabase: MemoryDatabase? = MemoryDatabase.getInstance(context)
) : MemoryRepository {
    private val memoryDao: MemoryDao? = memoryDatabase?.memoryDao()

    override suspend fun getMemoryList(): List<Memory> {
        val dao = memoryDao ?: return emptyList()

        return toMemoryList(dao.getAll())
    }

    override suspend fun addMemory(memory: Memory): Boolean {
        val dao = memoryDao ?: return false

        return dao.insert(MemoryEntity.from(memory)) > 0
    }

    private fun toMemoryList(memoryEntityList: List<MemoryEntity>): List<Memory> {
        return memoryEntityList.map { Memory(it.expression, it.result) }
    }
}
