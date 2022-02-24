package edu.nextstep.camp.data

import edu.nextstep.camp.calculator.domain.CalculatorRepository
import edu.nextstep.camp.calculator.domain.model.Memories
import edu.nextstep.camp.calculator.domain.model.Memory
import edu.nextstep.camp.data.database.CalculatorDao
import edu.nextstep.camp.data.entity.MemoryEntity
import edu.nextstep.camp.data.mapper.mapToDomain
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.FutureTask

internal class CalculatorDefaultRepository(
    private val calculatorDao: CalculatorDao,
    private val executor: ExecutorService
) : CalculatorRepository {

    override fun addMemory(memory: Memory) {
        executor.execute {
            calculatorDao.addMemory(MemoryEntity.of(memory))
        }
    }

    override fun getMemories(): Memories {
        val task = FutureTask {
            Memories(calculatorDao.getMemoryList().map(MemoryEntity::mapToDomain))
        }

        executor.execute(task)
        return task.get()
    }
}