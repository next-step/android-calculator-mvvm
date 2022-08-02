package edu.nextstep.camp.calculator.data

import androidx.room.Entity
import edu.nextstep.camp.calculator.domain.EvaluationRecord

@Entity(tableName = "evaluation_record")
data class EvaluationRecordEntity(val expression: String, val result: String)

fun EvaluationRecordEntity.toEvaluationRecord() : EvaluationRecord {
    return EvaluationRecord(expression = this.expression, result = this.result)
}
