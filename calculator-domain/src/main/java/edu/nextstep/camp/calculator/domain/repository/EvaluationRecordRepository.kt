package edu.nextstep.camp.calculator.domain.repository

import edu.nextstep.camp.calculator.domain.EvaluationRecord

interface EvaluationRecordRepository {
    fun record(evaluationRecord: EvaluationRecord)
    fun getEvaluationHistory() : List<EvaluationRecord>
}
