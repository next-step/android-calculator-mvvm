package edu.nextstep.camp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LogDao {
    @Insert
    fun insert(log: Log)

    @Query("SELECT * FROM Log")
    fun getAll(): List<Log>

    @Query("SELECT *  FROM Log ORDER BY id DESC LIMIT 1")
    fun getLast(): Log
}