package camp.nextstep.edu.calculator.data.repository

import camp.nextstep.edu.calculator.data.ResultExpressionEntity
import camp.nextstep.edu.calculator.data.CalculatorDao
import camp.nextstep.edu.calculator.data.CalculatorDatabase
import camp.nextstep.edu.calculator.domain.data.ResultExpression
import camp.nextstep.edu.calculator.domain.repository.ResultExpressionRepository
import android.content.Context

internal class ResultExpressionDbRepository(
    context: Context,
    calculatorDatabase: CalculatorDatabase? = CalculatorDatabase.getInstance(context)
) : ResultExpressionRepository {
    private val calculatorDao: CalculatorDao? = calculatorDatabase?.getDao()

    override suspend fun getResultExpressionList(): List<ResultExpression> {
        val dao = calculatorDao ?: return emptyList()

        return toResultExpressionList(dao.getAll())
    }

    override suspend fun addResultExpression(resultExpression: ResultExpression): Boolean {
        val dao = calculatorDao ?: return false

        return dao.insert(ResultExpressionEntity.from(resultExpression)) > 0
    }

    private fun toResultExpressionList(resultExpressionEntityList: List<ResultExpressionEntity>): List<ResultExpression> {
        return resultExpressionEntityList.map { ResultExpression(it.expression, it.result) }
    }
}
