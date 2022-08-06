package edu.nextstep.camp.calculator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CalculationHistoryDao {

    @Query("SELECT * FROM histories")
    suspend fun getCalculationHistories(): List<CalculationHistoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCalculationHistory(calculationHistoryEntity: CalculationHistoryEntity)

}
