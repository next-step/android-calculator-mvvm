package edu.nextstep.camp.calculator.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.nextstep.camp.calculator.domain.CalculationResult
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

@Entity(tableName = "calculation_result")
data class CalculationResultEntity(
    val expression: String,
    val result: Int,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
) {
    fun toCalculationResult(): CalculationResult =
        CalculationResult(savedStringToExpression(), result)

    private fun savedStringToExpression(): Expression =
        Expression(expression.split(" ").map(::stringToOperational))

    private fun stringToOperational(string: String): Any {
        val operatorOrNull = Operator.of(string)
        if (operatorOrNull != null)
            return operatorOrNull
        return string.toInt()
    }
}