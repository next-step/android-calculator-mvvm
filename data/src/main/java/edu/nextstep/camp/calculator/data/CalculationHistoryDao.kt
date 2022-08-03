package edu.nextstep.camp.calculator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CalculationHistoryDao {
    @Query("SELECT * FROM calculation_history")
    suspend fun getAll(): List<CalculationHistoryEntity>

    @Insert
    suspend fun insert(vararg entities: CalculationHistoryEntity)
}