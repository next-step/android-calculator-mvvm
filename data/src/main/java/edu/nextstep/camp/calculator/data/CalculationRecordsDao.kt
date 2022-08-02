package edu.nextstep.camp.calculator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * 클래스에 대한 간단한 설명이나 참고 url을 남겨주세요.
 * Created by jeongjinhong on 2022. 08. 02..
 */
@Dao
interface CalculationRecordDao {
    @Query("SELECT * FROM calculationRecord")
    suspend fun getAll(): List<CalculationRecord>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(calculationRecord: CalculationRecord)


}