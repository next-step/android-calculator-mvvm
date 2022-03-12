package edu.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.domain.HistoryRepository
import edu.nextstep.camp.calculator.domain.model.Memory

internal class HistoryRepositoryImpl(private val historyDao: HistoryDao): HistoryRepository {
    override suspend fun getAll(): List<Memory> {
        return historyDao.getAll().map {
            it.convertHistoryToMemory()
        }
    }

    override suspend fun insert(memory: Memory) {
        historyDao.insert(
            History(
                expression = memory.expression,
                resultValue = memory.resultValue
            )
        )
    }
}