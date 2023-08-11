package camp.nextstep.edu.calculator.domain

import kotlinx.coroutines.flow.Flow

interface RecordRepository {

	fun getAll(): Flow<List<RecordResource>>
	suspend fun insert(expression: Expression, result: Int)
}