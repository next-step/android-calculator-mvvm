package camp.nextstep.edu.calculator.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import camp.nextstep.edu.calculator.data.datasource.local.entity.CalculationResultEntity

@Dao
interface CalculationResultDao {

    @Query("select * from calculation_result")
    suspend fun selectAll(): List<CalculationResultEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(calculatorResult: CalculationResultEntity)
}