package edu.nextstep.camp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
internal interface CalculationRecordsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCalculationRecords(vararg calculationRecordEntities: CalculationRecordEntity)

    @Query("SELECT * FROM calculation_records")
    suspend fun loadCalculationRecords(): List<CalculationRecordEntity>
}

