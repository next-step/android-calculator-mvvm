package com.nextstep.camp.calculator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecordDao {

    @Query("SELECT * FROM Record")
    suspend fun getAll(): List<Record>

    @Insert
    suspend fun insert(record: Record)
}
