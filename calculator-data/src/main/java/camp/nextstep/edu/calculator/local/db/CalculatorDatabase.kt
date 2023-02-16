package camp.nextstep.edu.calculator.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import camp.nextstep.edu.calculator.local.converter.ExpressionListTypeConverter
import camp.nextstep.edu.calculator.local.dao.CalculatorResultDao
import camp.nextstep.edu.calculator.local.entity.CalculatorResultEntity

@Database(entities = [CalculatorResultEntity::class], version = 1, exportSchema = false)
@TypeConverters(ExpressionListTypeConverter::class)
abstract class CalculatorDatabase : RoomDatabase() {
    abstract fun calculatorResultDao(): CalculatorResultDao
}