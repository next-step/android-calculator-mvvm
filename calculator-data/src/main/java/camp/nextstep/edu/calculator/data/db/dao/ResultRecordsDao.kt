package camp.nextstep.edu.calculator.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import camp.nextstep.edu.calculator.data.db.entity.ResultEntity


@Dao
interface ResultRecordsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveResult(result: ResultEntity)

    @Query("select * from result_records")
    fun getAllResultRecords(): List<ResultEntity>?
}
