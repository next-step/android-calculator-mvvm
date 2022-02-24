package edu.nextstep.camp.calculator.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ResultRecordDao {
    @Query("SELECT * FROM result_record")
    fun getAll(): MutableList<ResultRecord>

    @Insert
    fun insertResultRecords(vararg resultRecords: ResultRecord)

    @Insert
    fun insertResultRecord(resultRecord: ResultRecord)

    @Delete
    fun delete(resultRecord: ResultRecord)
}