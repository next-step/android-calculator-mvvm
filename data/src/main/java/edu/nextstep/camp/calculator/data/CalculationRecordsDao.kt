package edu.nextstep.camp.calculator.data

import androidx.room.*

/**
 * 클래스에 대한 간단한 설명이나 참고 url을 남겨주세요.
 * Created by jeongjinhong on 2022. 08. 02..
 */
@Dao
interface CalculationRecordsDao {
    @Query("SELECT * FROM calculationRecords")
    suspend fun getAll(): List<CalculationRecords>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorites: CalculationRecords)

    @Delete
    suspend fun delete(favorites: CalculationRecords)

}