package edu.nextstep.camp.calculator.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
internal interface MemoryDao {
    @Query("SELECT * FROM memory")
    fun getAll(): Flow<List<MemoryEntity>>

    @Insert
    suspend fun insert(memory: MemoryEntity)
}
