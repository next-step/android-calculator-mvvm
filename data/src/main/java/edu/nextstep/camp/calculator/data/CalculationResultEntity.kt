package edu.nextstep.camp.calculator.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.nextstep.camp.calculator.domain.CalculationResult
import edu.nextstep.camp.calculator.domain.Expression

@Entity(tableName = "calculation_result")
internal data class CalculationResultEntity internal constructor (
    val expression: String,
    val result: Int,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
) {
    fun toCalculationResult(): CalculationResult =
        CalculationResult(Expression.from(expression), result)


    companion object {
        fun calculationResultToEntity(calculationResult: CalculationResult) =
            CalculationResultEntity(
                calculationResult.expression.toString(),
                calculationResult.result
            )
    }
}