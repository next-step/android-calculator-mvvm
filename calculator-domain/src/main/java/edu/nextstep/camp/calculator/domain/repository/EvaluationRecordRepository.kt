package edu.nextstep.camp.calculator.domain.repository

import edu.nextstep.camp.calculator.domain.model.EvaluationRecord
import kotlinx.coroutines.flow.Flow

interface EvaluationRecordRepository {
    suspend fun record(evaluationRecord: EvaluationRecord)
    fun getEvaluationHistory() : Flow<List<EvaluationRecord>>
}
