package camp.nextstep.edu.calculator.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MemoryDao {
    @Query("SELECT * FROM MemoryEntity")
    fun getAll(): List<MemoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(memoryEntity: MemoryEntity): Long

    @Delete
    fun delete(memoryEntity: MemoryEntity)
}
