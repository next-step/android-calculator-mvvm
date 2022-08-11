package edu.nextstep.camp.calculator.data.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import edu.nextstep.camp.calculator.data.model.ExpressionHistoryEntity

@Dao
interface ExpressionHistoryDAO {
    @Query("SELECT * FROM expression_history")
    suspend fun getAll(): List<ExpressionHistoryEntity>

    @Insert
    suspend fun insert(history: ExpressionHistoryEntity)
}