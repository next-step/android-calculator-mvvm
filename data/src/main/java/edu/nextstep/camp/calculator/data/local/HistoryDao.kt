package edu.nextstep.camp.calculator.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
internal interface HistoryDao {
    @Insert
    suspend fun insert(histories: List<History>)

    @Query("SELECT * FROM history")
    suspend fun getAll(): List<History>
}