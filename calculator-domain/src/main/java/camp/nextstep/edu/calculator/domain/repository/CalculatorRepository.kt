/**
 * @author Daewon on 27,August,2023
 *
 */

package camp.nextstep.edu.calculator.domain.repository

import camp.nextstep.edu.calculator.domain.Memory


interface CalculatorRepository {
    suspend fun saveMemory(expression: String, result: Int)
    suspend fun findMemories(): List<Memory>
}
