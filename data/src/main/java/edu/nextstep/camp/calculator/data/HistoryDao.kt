package edu.nextstep.camp.calculator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Created by link.js on 2022. 07. 31..
 */
@Dao
abstract class HistoryDao {
    @Query("SELECT * FROM HistoryEntity")
    abstract suspend fun getAll(): List<HistoryEntity>

    @Insert
    abstract suspend fun insertAll(histories: List<HistoryEntity>)

    @Query("DELETE FROM HistoryEntity")
    abstract suspend fun deleteAll()

    open suspend fun setHistories(histories: List<HistoryEntity>) {
        deleteAll()
        insertAll(histories)
    }
}