package camp.nextstep.edu.calculator.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveRecord(recordEntity: RecordEntity)

    @Query("SELECT * FROM record_table")
    fun loadRecords(): List<RecordEntity>
}
