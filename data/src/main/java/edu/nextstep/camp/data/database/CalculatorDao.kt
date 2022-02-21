package edu.nextstep.camp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import edu.nextstep.camp.data.entity.MemoryEntity

@Dao
internal interface CalculatorDao {

    @Insert
    fun addMemory(memory: MemoryEntity)

    @Query("SELECT * from memory")
    fun getMemoryList(): List<MemoryEntity>
}