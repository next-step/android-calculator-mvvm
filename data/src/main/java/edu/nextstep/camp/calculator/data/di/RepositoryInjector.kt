package edu.nextstep.camp.calculator.data.di

import edu.nextstep.camp.calculator.data.datasource.local.ExpressionHistoryLocalDataSource
import edu.nextstep.camp.calculator.data.repository.ExpressionHistoryRepositoryImpl
import edu.nextstep.camp.calculator.domain.repository.ExpressionHistoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object RepositoryInjector {

    fun provideExpressionHistoryRepository(
        expressionHistoryLocalDataSource: ExpressionHistoryLocalDataSource,
        ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    ): ExpressionHistoryRepository =
        ExpressionHistoryRepositoryImpl(
            expressionHistoryLocalDataSource = expressionHistoryLocalDataSource,
            ioDispatcher = ioDispatcher
        )
}