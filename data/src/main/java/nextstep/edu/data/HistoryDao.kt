package nextstep.edu.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
internal interface HistoryDao {
    @Query("SELECT * FROM history")
    fun getAll(): List<History>

    @Insert
    fun insertHistory(history: History)
}