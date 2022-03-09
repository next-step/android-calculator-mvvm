package util

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

object ExpressionUtil {
    fun String.toExpression(): Expression {
        if(this.isEmpty()) return Expression.EMPTY
        val splitString = this.split(" ")
        val expressionParam: List<Any> = splitString.mapIndexed { index, s ->
            return@mapIndexed if(index % 2 == 0) {
                s.toInt()
            } else {
                Operator.of(s) as Any
            }
        }
        return Expression(expressionParam)
    }
}