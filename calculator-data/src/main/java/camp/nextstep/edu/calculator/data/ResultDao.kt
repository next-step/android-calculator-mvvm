package camp.nextstep.edu.calculator.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ResultDao {
    @Query("SELECT * FROM Result")
    fun getAll(): List<Result>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertResult(result: Result): Long

    @Delete
    fun delete(result: Result)
}
