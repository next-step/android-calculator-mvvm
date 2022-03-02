package edu.nextstep.camp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MemoryDao {
    @Query("SELECT * FROM memory")
    fun getAll(): List<Memory>

    @Insert
    fun insert(vararg memory: Memory)

    @Query("SELECT * FROM memory WHERE id LIKE :id LIMIT 1")
    fun getMemoryById(id: Int): Memory
}