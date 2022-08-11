package edu.nextstep.camp.calculator.data.datasource.local

import edu.nextstep.camp.calculator.domain.model.ExpressionHistory

interface ExpressionHistoryLocalDataSource {
    suspend fun getAll(): List<ExpressionHistory>
    suspend fun insert(history: ExpressionHistory)
}