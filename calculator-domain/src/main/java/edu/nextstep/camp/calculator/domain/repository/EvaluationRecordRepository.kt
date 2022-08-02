package edu.nextstep.camp.calculator.domain.repository

import edu.nextstep.camp.calculator.domain.EvaluationRecord
import kotlinx.coroutines.flow.Flow

interface EvaluationRecordRepository {
    suspend fun record(evaluationRecord: EvaluationRecord)
    suspend fun getEvaluationHistory() : Flow<List<EvaluationRecord>>
}
