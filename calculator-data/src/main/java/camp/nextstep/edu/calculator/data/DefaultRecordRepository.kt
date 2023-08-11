package camp.nextstep.edu.calculator.data

import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.RecordRepository
import camp.nextstep.edu.calculator.domain.RecordResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class DefaultRecordRepository(private val recordDao: RecordDao): RecordRepository {

	override fun getAll(): Flow<List<RecordResource>> {
		return recordDao.getAll().map { it.map(RecordEntity::asRecordResource) }
	}

	override suspend fun insert(expression: Expression, result: Int) {
		recordDao.insert(
			RecordEntity(
				expression = expression.toString(),
				result = result
			)
		)
	}
}