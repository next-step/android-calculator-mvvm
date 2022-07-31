package edu.nextstep.camp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Created by link.js on 2022. 07. 31..
 */
@Dao
interface HistoryDao {
    @Query("SELECT * FROM history")
    suspend fun getAll(): List<History>

    @Insert
    suspend fun insertAll(vararg users: List<History>)

    @Query("DELETE FROM history")
    suspend fun deleteAll()
}