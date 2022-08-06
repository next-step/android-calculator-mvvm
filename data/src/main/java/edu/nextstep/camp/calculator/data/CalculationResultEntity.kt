package edu.nextstep.camp.calculator.data

import androidx.core.text.isDigitsOnly
import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.nextstep.camp.calculator.domain.CalculationResult
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

@Entity(tableName = "calculation_result")
data class CalculationResultEntity internal constructor(
    val expression: String,
    val result: Int,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
) {
    fun toCalculationResult(): CalculationResult =
        CalculationResult(savedStringToExpression(), result)

    private fun savedStringToExpression(): Expression =
        Expression(expression.split(" ").map(::stringToOperational))

    private fun stringToOperational(string: String): Any {
        if (string.isDigitsOnly())
            return string.toInt()
        val operatorOrNull = Operator.of(string)
        if (operatorOrNull != null)
            return operatorOrNull
        return IllegalArgumentException()
    }

    companion object {
        fun calculationResultToEntity(calculationResult: CalculationResult) =
            CalculationResultEntity(
                calculationResult.expression.toString(),
                calculationResult.result
            )
    }
}