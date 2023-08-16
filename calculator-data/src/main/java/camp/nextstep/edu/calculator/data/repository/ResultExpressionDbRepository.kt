package camp.nextstep.edu.calculator.data.repository

import camp.nextstep.edu.calculator.data.MemoryEntity
import camp.nextstep.edu.calculator.data.MemoryDao
import camp.nextstep.edu.calculator.data.MemoryDatabase
import camp.nextstep.edu.calculator.domain.data.ResultExpression
import camp.nextstep.edu.calculator.domain.repository.ResultExpressionRepository
import android.content.Context

internal class ResultExpressionDbRepository(
    context: Context,
    memoryDatabase: MemoryDatabase? = MemoryDatabase.getInstance(context)
) : ResultExpressionRepository {
    private val memoryDao: MemoryDao? = memoryDatabase?.memoryDao()

    override suspend fun getResultExpressionList(): List<ResultExpression> {
        val dao = memoryDao ?: return emptyList()

        return toResultExpressionList(dao.getAll())
    }

    override suspend fun addResultExpression(resultExpression: ResultExpression): Boolean {
        val dao = memoryDao ?: return false

        return dao.insert(MemoryEntity.from(resultExpression)) > 0
    }

    private fun toResultExpressionList(memoryEntityList: List<MemoryEntity>): List<ResultExpression> {
        return memoryEntityList.map { ResultExpression(it.expression, it.result) }
    }
}
