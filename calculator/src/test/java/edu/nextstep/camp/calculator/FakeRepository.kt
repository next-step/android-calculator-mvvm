package edu.nextstep.camp.calculator

import androidx.annotation.VisibleForTesting
import edu.nextstep.camp.calculator.domain.HistoryRepository
import edu.nextstep.camp.calculator.domain.model.Memory
import java.util.LinkedHashMap

class FakeRepository : HistoryRepository{

    var memoriesServiceData: LinkedHashMap<String, Memory> = LinkedHashMap()

    override suspend fun getAll(): List<Memory> {
        return memoriesServiceData.values.toList()
    }

    override suspend fun insert(memory: Memory) {
        memoriesServiceData[memory.expression] = memory
    }

    @VisibleForTesting
    fun addMemory(vararg memories: Memory) {
        for (memory in memories) {
            memoriesServiceData[memory.expression] = memory
        }
    }
}