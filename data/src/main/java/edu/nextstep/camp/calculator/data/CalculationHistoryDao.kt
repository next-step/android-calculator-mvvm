package edu.nextstep.camp.calculator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CalculationHistoryDao {
    @Query("SELECT * FROM calculationhistoryentity")
    fun getAll(): List<CalculationHistoryEntity>

    @Insert
    fun insert(entities: List<CalculationHistoryEntity>)
}