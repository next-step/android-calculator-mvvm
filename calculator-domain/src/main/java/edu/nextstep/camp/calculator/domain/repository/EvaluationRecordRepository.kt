package edu.nextstep.camp.calculator.domain.repository

import edu.nextstep.camp.calculator.domain.model.EvaluationRecord

interface EvaluationRecordRepository {
    suspend fun record(evaluationRecord: EvaluationRecord)
    fun getEvaluationHistory() : List<EvaluationRecord>
}
