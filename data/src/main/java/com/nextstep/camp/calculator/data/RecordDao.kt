package com.nextstep.camp.calculator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
internal interface RecordDao {

    @Query("SELECT * FROM RecordEntity")
    suspend fun getAll(): List<RecordEntity>

    @Insert
    suspend fun insert(record: RecordEntity)
}
