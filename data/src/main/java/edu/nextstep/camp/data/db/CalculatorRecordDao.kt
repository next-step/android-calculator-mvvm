package edu.nextstep.camp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import edu.nextstep.camp.data.model.CalculatorRecordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CalculatorRecordDao {
    @Query("SELECT * FROM CalculatorRecordEntity")
    fun getAllRecord(): Flow<List<CalculatorRecordEntity>>

    @Insert
    fun insertRecord(record: CalculatorRecordEntity)
}
