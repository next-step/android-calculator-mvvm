package edu.nextstep.camp.calculator.data.datasource.local

import edu.nextstep.camp.calculator.data.mapper.mapper
import edu.nextstep.camp.calculator.data.model.ExpressionHistoryEntity
import edu.nextstep.camp.calculator.data.service.ExpressionHistoryDAO
import edu.nextstep.camp.calculator.domain.model.ExpressionHistory

internal class ExpressionHistoryLocalDataSourceImpl(
    private val expressionHistoryDAO: ExpressionHistoryDAO
) : ExpressionHistoryLocalDataSource {
    override suspend fun getAll(): List<ExpressionHistory> = expressionHistoryDAO.getAll().mapper()
    override suspend fun insert(history: ExpressionHistory) =
        expressionHistoryDAO.insert(
            ExpressionHistoryEntity(
                expression = history.expression,
                result = history.result
            )
        )
}