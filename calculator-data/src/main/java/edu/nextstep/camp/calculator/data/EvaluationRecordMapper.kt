package edu.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.domain.EvaluationRecord

fun EvaluationRecord.toEntity() : EvaluationRecordEntity =
    EvaluationRecordEntity(expression = this.expression, result = this.result)

fun List<EvaluationRecordEntity>.toEvaluationRecordList() : List<EvaluationRecord> =
    this.map {
        EvaluationRecord(expression = it.expression, result = it.result)
    }
