package edu.nextstep.camp.calculator.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {
    @Insert
    fun insert(histories: List<History>)

    @Query("SELECT * FROM history")
    fun getAll(): List<History>
}