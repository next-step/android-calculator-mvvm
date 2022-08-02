package edu.nextstep.camp.calculator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EvaluationRecordDao {
    @Query("SELECT * FROM evaluation_record")
    fun getAll(): List<EvaluationRecordEntity>

    @Insert
    fun insert(record: EvaluationRecordEntity)
}
