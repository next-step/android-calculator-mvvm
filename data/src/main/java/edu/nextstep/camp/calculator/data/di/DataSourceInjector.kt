package edu.nextstep.camp.calculator.data.di

import edu.nextstep.camp.calculator.data.datasource.local.ExpressionHistoryLocalDataSource
import edu.nextstep.camp.calculator.data.datasource.local.ExpressionHistoryLocalDataSourceImpl
import edu.nextstep.camp.calculator.data.service.ExpressionHistoryDAO

object DataSourceInjector {
    fun provideExpressionHistoryLocalDataSource(expressionHistoryDAO: ExpressionHistoryDAO): ExpressionHistoryLocalDataSource =
        ExpressionHistoryLocalDataSourceImpl(expressionHistoryDAO)
}