package edu.nextstep.camp.calculator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CalculationResultDao {
    @Query("SELECT * FROM calculation_result")
    suspend fun getAll(): List<CalculationResultEntity>

    @Insert
    suspend fun insert(vararg entities: CalculationResultEntity)
}