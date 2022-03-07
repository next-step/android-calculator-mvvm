package edu.nextstep.camp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CalculatorDao {

    @Query("SELECT * FROM calculatorHistory")
    fun getCalculatorHisotry(): List<CalculatorHistory>

    @Insert
    fun insertCalculatorHisotry(calculatorHistory: CalculatorHistory)
}