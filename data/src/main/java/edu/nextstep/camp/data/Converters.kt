package edu.nextstep.camp.data

import androidx.room.TypeConverter
import edu.nextstep.camp.domain.calculator.Expression

internal class Converters {
    @TypeConverter
    fun expressionToString(expression: Expression): String = expression.toString()

    @TypeConverter
    fun stringToExpression(value: String): Expression = Expression(value.split(" "))

}
