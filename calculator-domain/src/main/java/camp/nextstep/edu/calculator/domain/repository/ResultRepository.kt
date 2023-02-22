package camp.nextstep.edu.calculator.domain.repository

import camp.nextstep.edu.calculator.domain.model.CalculatorResult
import kotlinx.coroutines.flow.Flow


interface ResultRepository {

    fun saveResult(result: CalculatorResult)

    fun getAllResults(): Flow<List<CalculatorResult>>
}
