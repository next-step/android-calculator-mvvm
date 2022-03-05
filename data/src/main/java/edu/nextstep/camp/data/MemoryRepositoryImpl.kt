package edu.nextstep.camp.data

import edu.nextstep.camp.domain.Calculation
import edu.nextstep.camp.domain.CalculatorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class MemoryRepositoryImpl(private val memoryDao: MemoryDao) : CalculatorRepository {

    override suspend fun getAll(): List<Calculation> = withContext(Dispatchers.IO) {
        memoryDao.getAll().map {
            it.convertToCalculation()
        }
    }

    override suspend fun insert(calculation: Calculation) = withContext(Dispatchers.IO) {
        memoryDao.insert(
            Memory(
                expression = calculation.expression,
                result = calculation.result
            )
        )
    }

    private fun Memory.convertToCalculation(): Calculation {
        return Calculation(
            expression = this.expression,
            result = this.result
        )
    }
}