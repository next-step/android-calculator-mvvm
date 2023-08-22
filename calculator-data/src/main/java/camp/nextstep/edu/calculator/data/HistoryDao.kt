package camp.nextstep.edu.calculator.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {

    @Query("SELECT * FROM HistoryEntity")
    suspend fun getAll(): List<HistoryEntity>

    @Insert
    suspend fun insertAll(vararg histories: HistoryEntity)

    @Delete
    suspend fun delete(history: HistoryEntity)
}
