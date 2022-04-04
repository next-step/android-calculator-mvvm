package edu.nextstep.camp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
internal interface CalculatorDao {

    @Query("SELECT * FROM calculatorHistoryEntity")
    fun getCalculatorHisotry(): List<CalculatorHistoryEntity>

    @Insert
    fun insertCalculatorHisotry(calculatorHistoryEntity: CalculatorHistoryEntity)
}