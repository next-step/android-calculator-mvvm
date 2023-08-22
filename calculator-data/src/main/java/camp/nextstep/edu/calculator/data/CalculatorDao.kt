package camp.nextstep.edu.calculator.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
internal interface CalculatorDao {
    @Query("SELECT * FROM ResultExpressionEntity")
    fun getAll(): List<ResultExpressionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(resultExpressionEntity: ResultExpressionEntity): Long

    @Delete
    fun delete(resultExpressionEntity: ResultExpressionEntity)
}
