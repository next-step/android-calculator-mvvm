package camp.nextstep.edu.calculator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
internal interface RecordDao {

	@Query("SELECT * FROM RecordEntity ORDER BY id ASC")
	fun getAll(): Flow<List<RecordEntity>>

	@Insert
	suspend fun insert(recordEntity: RecordEntity)
}