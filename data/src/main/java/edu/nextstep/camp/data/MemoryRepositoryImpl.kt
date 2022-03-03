package edu.nextstep.camp.data

import edu.nextstep.camp.domain.Calculation
import edu.nextstep.camp.domain.CalculatorRepository

internal class MemoryRepositoryImpl(private val memoryDao: MemoryDao) : CalculatorRepository {

    override fun getAll(): List<Calculation> {
        return memoryDao.getAll().map {
            Calculation(
                expression = it.expression,
                result = it.result
            )
        }
    }

    override fun insert(calculation: Calculation) {
        memoryDao.insert(
            Memory(
                expression = calculation.expression,
                result = calculation.result
            )
        )
    }
}