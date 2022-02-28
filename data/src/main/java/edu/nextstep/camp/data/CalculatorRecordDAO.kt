package edu.nextstep.camp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CalculatorRecordDAO {
    @Query("SELECT expression, result FROM CalculatorRecord")
    fun getAllRecord(): Flow<List<CalculatorRecord>>

    @Insert
    fun insertRecord(record: CalculatorRecord)
}
