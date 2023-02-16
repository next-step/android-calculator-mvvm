package camp.nextstep.edu.calculator

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [CalculatorResultData::class], version = 1, exportSchema = false)
@TypeConverters(ExpressionListTypeConverter::class)
abstract class CalculatorDatabase : RoomDatabase() {
    abstract fun calculatorResultDao(): CalculatorResultDao
}