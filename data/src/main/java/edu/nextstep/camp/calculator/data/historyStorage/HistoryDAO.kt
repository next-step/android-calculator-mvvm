package edu.nextstep.camp.calculator.data.historyStorage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDAO {
    @Insert
    suspend fun insert(history: HistoryEntity)

    @Query("SELECT * FROM history")
    suspend fun getAll(): List<HistoryEntity>
}