package edu.nextstep.camp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LogDao {
    @Insert
    fun insert(log: LogEntity)

    @Query("SELECT * FROM LogEntity")
    fun getAll(): List<LogEntity>

    @Query("SELECT *  FROM LogEntity ORDER BY id DESC LIMIT 1")
    fun getLast(): LogEntity
}