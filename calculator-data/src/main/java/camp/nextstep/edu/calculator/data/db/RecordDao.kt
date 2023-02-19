package camp.nextstep.edu.calculator.data.db

import androidx.room.*
import camp.nextstep.edu.calculator.data.model.RecordEntity


@Dao
interface RecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: RecordEntity)

    @Delete
    suspend fun deleteRecord(record: RecordEntity)

    @Query("SELECT * FROM records")
    fun getRecords(): List<RecordEntity>
}