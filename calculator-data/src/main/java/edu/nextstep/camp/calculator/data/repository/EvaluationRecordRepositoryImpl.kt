package edu.nextstep.camp.calculator.data.repository

import edu.nextstep.camp.calculator.data.EvaluationRecordDao
import edu.nextstep.camp.calculator.data.toEntity
import edu.nextstep.camp.calculator.data.toEvaluationRecordList
import edu.nextstep.camp.calculator.domain.model.EvaluationRecord
import edu.nextstep.camp.calculator.domain.repository.EvaluationRecordRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

internal class EvaluationRecordRepositoryImpl constructor(
    private val dao: EvaluationRecordDao,
) : EvaluationRecordRepository {
    override suspend fun record(evaluationRecord: EvaluationRecord) {
        return dao.insert(evaluationRecord.toEntity())
    }

    override fun getEvaluationHistory(): Flow<List<EvaluationRecord>> {
        return dao.getAll().map { it.toEvaluationRecordList() }
    }
}
