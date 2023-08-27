/**
 * @author Daewon on 27,August,2023
 *
 */

package camp.nextstep.edu.calculator.data.repository

import camp.nextstep.edu.calculator.data.entity.MemoryEntity
import camp.nextstep.edu.calculator.data.local.CalculatorDao
import camp.nextstep.edu.calculator.domain.Memory
import camp.nextstep.edu.calculator.domain.repository.CalculatorRepository


class CalculatorRepositoryImpl(
    private val calculatorDao: CalculatorDao
) : CalculatorRepository {
    override suspend fun saveMemory(expression: String, result: Int) {
        calculatorDao.saveMemory(MemoryEntity.from(Memory(expression, result)))
    }

    override suspend fun findMemories(): List<Memory> {
        return calculatorDao.findMemories().map { it.toDomain() }
    }
}
