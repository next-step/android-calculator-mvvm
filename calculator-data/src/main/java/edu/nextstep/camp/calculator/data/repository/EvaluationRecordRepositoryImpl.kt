package edu.nextstep.camp.calculator.data.repository

import edu.nextstep.camp.calculator.data.EvaluationRecordDao
import edu.nextstep.camp.calculator.data.di.IoDispatcher
import edu.nextstep.camp.calculator.data.toEntity
import edu.nextstep.camp.calculator.data.toEvaluationRecordList
import edu.nextstep.camp.calculator.domain.model.EvaluationRecord
import edu.nextstep.camp.calculator.domain.repository.EvaluationRecordRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EvaluationRecordRepositoryImpl @Inject constructor(
    private val dao: EvaluationRecordDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : EvaluationRecordRepository {
    override suspend fun record(evaluationRecord: EvaluationRecord) {
        return withContext(ioDispatcher) { dao.insert(evaluationRecord.toEntity()) }
    }

    override suspend fun getEvaluationHistory(): List<EvaluationRecord> {
        return withContext(ioDispatcher) { dao.getAll().toEvaluationRecordList() }
    }
}
