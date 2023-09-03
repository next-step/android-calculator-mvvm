package camp.nextstep.edu.calculator.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import camp.nextstep.edu.calculator.data.entity.ResultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ResultDao {
    @Insert
    suspend fun insertResult(result: ResultEntity)

    @Query("SELECT * FROM result ORDER BY created_at DESC")
    fun getAllResult(): Flow<List<ResultEntity>>
}
