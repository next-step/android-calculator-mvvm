package camp.nextstep.edu.calculator.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import camp.nextstep.edu.calculator.data.room.entity.RecordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CalculatorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecord(recordEntity: RecordEntity)

    @Query("SELECT * FROM RecordEntity ORDER BY id DESC")
    fun getAllRecords(): Flow<List<RecordEntity>>
}