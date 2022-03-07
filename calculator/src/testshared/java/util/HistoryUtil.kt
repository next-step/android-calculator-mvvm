package util

import edu.nextstep.camp.calculator.domain.repository.History
import util.ExpressionUtil.toExpression

object HistoryUtil {
    fun historyOf(expression: String, result: String): History =
        History(expression.toExpression(), result.toExpression())
}