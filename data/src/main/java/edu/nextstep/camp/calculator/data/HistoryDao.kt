package edu.nextstep.camp.calculator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Created by link.js on 2022. 07. 31..
 */
@Dao
abstract class HistoryDao {
    @Query("SELECT * FROM history")
    abstract suspend fun getAll(): List<History>

    @Insert
    abstract suspend fun insertAll(histories: List<History>)

    @Query("DELETE FROM history")
    abstract suspend fun deleteAll()

    open suspend fun setHistories(histories: List<History>) {
        deleteAll()
        insertAll(histories)
    }
}