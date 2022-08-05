package edu.nextstep.camp.calculator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history")
    suspend fun getHistoryList(): List<HistoryEntity>

    @Insert
    suspend fun insertHistory(history: HistoryEntity)
}