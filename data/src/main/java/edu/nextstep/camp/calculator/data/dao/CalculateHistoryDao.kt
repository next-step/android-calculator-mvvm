package edu.nextstep.camp.calculator.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import edu.nextstep.camp.calculator.data.model.CalculateResultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CalculateResultDao {
    @Insert
    suspend fun insertCalculateResult(calculateResultEntity: CalculateResultEntity)

    @Query("select * from calculate_result")
    fun getCalculateResults(): Flow<List<CalculateResultEntity>?>
}
