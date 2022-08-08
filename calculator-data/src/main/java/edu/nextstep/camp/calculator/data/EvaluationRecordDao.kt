package edu.nextstep.camp.calculator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EvaluationRecordDao {
    @Query("SELECT * FROM evaluation_record")
    fun getAll(): Flow<List<EvaluationRecordEntity>>

    @Insert
    suspend fun insert(record: EvaluationRecordEntity)
}
