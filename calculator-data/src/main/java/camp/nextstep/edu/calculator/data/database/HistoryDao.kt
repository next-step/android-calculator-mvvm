package camp.nextstep.edu.calculator.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(historyEntity: HistoryEntity)

    @Delete
    suspend fun deleteHistory(historyEntity: HistoryEntity)

    @Query("SELECT * FROM HistoryEntity")
    fun getHistories(): List<HistoryEntity>
}