package edu.nextstep.camp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CalculatorDao {

    @Query("SELECT * FROM calculatorHisotry")
    fun getCalculatorHisotry(): List<CalculatorHisotry>

    @Insert
    fun insertCalculatorHisotry(calculatorHisotry: CalculatorHisotry)
}