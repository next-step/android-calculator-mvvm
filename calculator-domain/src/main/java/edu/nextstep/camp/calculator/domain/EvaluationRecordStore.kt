package edu.nextstep.camp.calculator.domain

class EvaluationRecordStore {
    private val evaluationHistory = mutableListOf<EvaluationRecord>()

    fun record(evaluationRecord: EvaluationRecord) {
        evaluationHistory.add(evaluationRecord)
    }

    fun getEvaluationHistory() = evaluationHistory.toList()
}
