package edu.nextstep.camp.calculator

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoryDao {

    @Query("SELECT * FROM memory")
    fun getAllMemory(): Flow<List<Memory>>

    @Query("DELETE FROM memory")
    suspend fun clearAll()

    @Insert
    suspend fun insert(vararg memory: Memory)

    @Delete
    suspend fun delete(vararg memory: Memory)
}