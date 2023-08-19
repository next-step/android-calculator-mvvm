package camp.nextstep.edu.calculator.domain.repository

import camp.nextstep.edu.calculator.domain.data.ResultExpression

interface ResultExpressionRepository {
    suspend fun getResultExpressionList(): List<ResultExpression>
    suspend fun addResultExpression(resultExpression: ResultExpression): Boolean
}
