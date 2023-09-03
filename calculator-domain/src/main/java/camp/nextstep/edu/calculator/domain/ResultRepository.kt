package camp.nextstep.edu.calculator.domain

import kotlinx.coroutines.flow.Flow

interface ResultRepository {

    val resultList : Flow<List<Result>>

    fun getResults(): Flow<List<Result>>

    suspend fun putResult(expression: String, result: Int)
}
