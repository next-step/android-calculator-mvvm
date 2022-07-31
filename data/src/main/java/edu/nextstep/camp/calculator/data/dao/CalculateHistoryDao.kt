package edu.nextstep.camp.calculator.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import edu.nextstep.camp.calculator.data.model.CalculateHistoryEntity

@Dao
interface CalculateHistoryDao {
    @Insert
    suspend fun insertCalculateHistory(calculateHistoryEntity: CalculateHistoryEntity)

    @Query("select * from calculate_history")
    suspend fun getCalculateHistories(): List<CalculateHistoryEntity>?
}
