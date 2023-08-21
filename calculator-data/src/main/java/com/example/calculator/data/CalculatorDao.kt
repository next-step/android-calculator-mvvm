package com.example.calculator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CalculatorDao {
    @Insert
    fun insertMemory(memory: MemoryEntity)

    @Query("SELECT * FROM memories")
    fun getMemories(): List<MemoryEntity>
}
