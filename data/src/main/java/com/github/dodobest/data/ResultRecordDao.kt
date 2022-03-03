package com.github.dodobest.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ResultRecordDao {
    @Query("SELECT * FROM result_record")
    fun getAll(): MutableList<ResultRecord>

    @Query("SELECT * FROM result_record WHERE expression = :expression")
    fun findResultRecordByExpression(expression: String): ResultRecord

    @Insert
    fun insertResultRecords(vararg resultRecords: ResultRecord)

    @Insert
    fun insertResultRecord(resultRecord: ResultRecord)

    @Delete
    fun delete(resultRecord: ResultRecord)
}