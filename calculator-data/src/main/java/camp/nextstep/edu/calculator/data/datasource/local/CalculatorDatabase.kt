package camp.nextstep.edu.calculator.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import camp.nextstep.edu.calculator.data.datasource.local.dao.CalculationResultDao
import camp.nextstep.edu.calculator.data.datasource.local.entity.CalculationResultEntity

@Database(entities = [CalculationResultEntity::class], version = 1)
abstract class CalculatorDatabase : RoomDatabase() {
    abstract fun calculatorResultDao(): CalculationResultDao
}