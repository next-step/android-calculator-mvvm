package camp.nextstep.edu.calculator.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import camp.nextstep.edu.calculator.local.entity.CalculatorResultEntity

@Dao
interface CalculatorResultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(calculatorResultData: CalculatorResultEntity)

    @Query("select * from calculatorResultData")
    fun getResultList(): List<CalculatorResultEntity>
}