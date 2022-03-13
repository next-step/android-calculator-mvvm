package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.HistoryRepository
import edu.nextstep.camp.calculator.domain.model.Memory

class FakeRepository(vararg memories: Memory) : HistoryRepository {
    var memoriesServiceData: LinkedHashMap<String, Memory> = LinkedHashMap()

    init {
        for (memory in memories) {
            memoriesServiceData[memory.expression] = memory
        }
    }

    override suspend fun getAll(): List<Memory> {
        return memoriesServiceData.values.toList()
    }

    override suspend fun insert(memory: Memory) {
        memoriesServiceData[memory.expression] = memory
    }

}