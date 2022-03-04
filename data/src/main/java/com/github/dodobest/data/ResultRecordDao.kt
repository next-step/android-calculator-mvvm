package com.github.dodobest.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ResultRecordDao {
    @Query("SELECT * FROM result_record")
    fun getAll(): List<ResultRecordEntity>

    @Query("SELECT * FROM result_record WHERE expression = :expression")
    fun findResultRecordByExpression(expression: String): ResultRecordEntity

    @Insert
    fun insertResultRecords(vararg resultRecords: ResultRecordEntity)

    @Insert
    fun insertResultRecord(resultRecord: ResultRecordEntity)

    @Delete
    fun delete(resultRecord: ResultRecordEntity)
}