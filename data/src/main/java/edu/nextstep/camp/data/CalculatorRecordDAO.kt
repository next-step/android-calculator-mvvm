package edu.nextstep.camp.data

import androidx.room.Dao
import androidx.room.Query
import edu.nextstep.camp.domain.calculator.CalculatorMemory

@Dao
interface CalculatorRecordDAO {
    @Query("SELECT expression, result FROM CalculatorRecord")
    fun getAllRecord(): List<CalculatorMemory.Record>
}
