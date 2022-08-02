package edu.nextstep.camp.calculator.domain.repository

import edu.nextstep.camp.calculator.domain.model.EvaluationRecord

interface EvaluationRecordRepository {
    suspend fun record(evaluationRecord: EvaluationRecord)
    suspend fun getEvaluationHistory() : List<EvaluationRecord>
}
