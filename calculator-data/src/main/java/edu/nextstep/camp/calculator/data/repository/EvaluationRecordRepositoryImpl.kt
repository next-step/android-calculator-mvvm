package edu.nextstep.camp.calculator.data.repository

import edu.nextstep.camp.calculator.data.CalculatorDatabase
import edu.nextstep.camp.calculator.data.toEntity
import edu.nextstep.camp.calculator.data.toEvaluationRecordList
import edu.nextstep.camp.calculator.domain.model.EvaluationRecord
import edu.nextstep.camp.calculator.domain.repository.EvaluationRecordRepository
import javax.inject.Inject

class EvaluationRecordRepositoryImpl @Inject constructor(
    private val db: CalculatorDatabase
) : EvaluationRecordRepository {
    override suspend fun record(evaluationRecord: EvaluationRecord) {
        db.evaluationRecordDao().insert(evaluationRecord.toEntity())
    }

    override fun getEvaluationHistory(): List<EvaluationRecord> {
        return db.evaluationRecordDao().getAll().toEvaluationRecordList()
    }
}
