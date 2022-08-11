package edu.nextstep.camp.calculator.domain.repository

import edu.nextstep.camp.calculator.domain.model.ExpressionHistory

interface ExpressionHistoryRepository {
    suspend fun getAll(): Result<List<ExpressionHistory>>
    suspend fun insert(history: ExpressionHistory): Result<Unit>
}
