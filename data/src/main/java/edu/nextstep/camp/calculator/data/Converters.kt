package edu.nextstep.camp.calculator.data

import androidx.room.TypeConverter
import edu.nextstep.camp.calculator.domain.Expression

internal class Converters {

    @TypeConverter
    fun expressionToString(expression: Expression): String = expression.toString()

    @TypeConverter
    fun stringToExpression(value: String): Expression = Expression(value.split(" "))

}