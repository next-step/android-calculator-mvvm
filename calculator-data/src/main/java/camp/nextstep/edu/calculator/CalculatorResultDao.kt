package camp.nextstep.edu.calculator

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CalculatorResultDao {
    @Insert
    fun save(calculatorResultData: CalculatorResultData)

    @Query("select * from calculatorResultData")
    fun getResultList(): List<CalculatorResultData>
}