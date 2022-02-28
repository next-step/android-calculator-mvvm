package edu.nextstep.camp.calculator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CalculatorDao {

    @Query("SELECT * FROM calculationmemory")
    fun getCalculationMemoryAll(): List<CalculationMemory>

    @Insert
    fun insertCalculationMemory(calculationMemory: CalculationMemory)
}