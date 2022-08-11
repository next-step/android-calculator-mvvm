package edu.nextstep.camp.calculator.data.di

import edu.nextstep.camp.calculator.data.datasource.local.ExpressionHistoryLocalDataSource
import edu.nextstep.camp.calculator.data.repository.ExpressionHistoryRepositoryImpl
import edu.nextstep.camp.calculator.domain.repository.ExpressionHistoryRepository

object RepositoryInjector {

    fun provideExpressionHistoryRepository(expressionHistoryLocalDataSource: ExpressionHistoryLocalDataSource): ExpressionHistoryRepository =
        ExpressionHistoryRepositoryImpl(expressionHistoryLocalDataSource)
}