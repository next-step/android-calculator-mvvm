package edu.nextstep.camp.calculator.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import edu.nextstep.camp.calculator.data.model.CalculateHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CalculateHistoryDao {
    @Insert
    suspend fun insertCalculateHistory(calculateHistoryEntity: CalculateHistoryEntity)

    @Query("select * from calculate_history")
    fun getCalculateHistories(): Flow<List<CalculateHistoryEntity>?>
}
