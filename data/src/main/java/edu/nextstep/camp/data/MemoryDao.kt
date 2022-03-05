package edu.nextstep.camp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
internal interface MemoryDao {
    @Query("SELECT * FROM memory")
    fun getAll(): List<Memory>

    @Insert
    fun insert(memory: Memory)
}