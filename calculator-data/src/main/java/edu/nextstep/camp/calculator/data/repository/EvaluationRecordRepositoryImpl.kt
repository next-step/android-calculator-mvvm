package edu.nextstep.camp.calculator.data.repository

import edu.nextstep.camp.calculator.data.CalculatorDatabase
import edu.nextstep.camp.calculator.data.toEntity
import edu.nextstep.camp.calculator.data.toEvaluationRecordList
import edu.nextstep.camp.calculator.domain.EvaluationRecord
import edu.nextstep.camp.calculator.domain.repository.EvaluationRecordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EvaluationRecordRepositoryImpl @Inject constructor(
    private val db: CalculatorDatabase
) : EvaluationRecordRepository {
    override suspend fun record(evaluationRecord: EvaluationRecord) {
        db.evaluationRecordDao().insert(evaluationRecord.toEntity())
    }

    override suspend fun getEvaluationHistory(): Flow<List<EvaluationRecord>> {
        return db.evaluationRecordDao().getAll().map { it.toEvaluationRecordList() }
    }
}
