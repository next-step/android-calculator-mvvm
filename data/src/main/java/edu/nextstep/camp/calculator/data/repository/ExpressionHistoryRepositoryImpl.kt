package edu.nextstep.camp.calculator.data.repository

import edu.nextstep.camp.calculator.data.datasource.local.ExpressionHistoryLocalDataSource
import edu.nextstep.camp.calculator.domain.model.ExpressionHistory
import edu.nextstep.camp.calculator.domain.repository.ExpressionHistoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class ExpressionHistoryRepositoryImpl(
    private val expressionHistoryLocalDataSource: ExpressionHistoryLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ExpressionHistoryRepository {
    override suspend fun getAll(): Result<List<ExpressionHistory>> = runCatching {
        withContext(ioDispatcher){
            expressionHistoryLocalDataSource.getAll()
        }
    }

    override suspend fun insert(history: ExpressionHistory): Result<Unit> = runCatching {
        withContext(ioDispatcher) {
            expressionHistoryLocalDataSource.insert(history)
        }
    }
}