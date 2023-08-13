package camp.nextstep.edu.calculator.data.repository

import android.app.Application
import camp.nextstep.edu.calculator.data.Result
import camp.nextstep.edu.calculator.data.ResultDao
import camp.nextstep.edu.calculator.data.ResultDatabase
import camp.nextstep.edu.calculator.domain.data.Memory
import camp.nextstep.edu.calculator.domain.repository.MemoryRepository

class MemoryDbRepository(application: Application) : MemoryRepository {
    private val resultDatabase: ResultDatabase? = ResultDatabase.getInstance(application)
    private val resultDao: ResultDao? = resultDatabase?.resultDao()

    override suspend fun getMemoryList(): List<Memory> {
        val dao = resultDao ?: return emptyList()

        return toMemoryList(dao.getAll())
    }

    override suspend fun addMemory(memory: Memory): Boolean {
        val dao = resultDao ?: return false

        return dao.insertResult(fromResult(memory)) > 0
    }

    private fun toMemoryList(resultList: List<Result>): List<Memory> {
        return resultList.map { Memory(it.expression, it.result) }
    }

    private fun fromResult(memory: Memory): Result {
        return Result(expression = memory.expression, result = memory.result)
    }
}
