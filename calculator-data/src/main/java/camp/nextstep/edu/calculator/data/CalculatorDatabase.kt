package camp.nextstep.edu.calculator.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import camp.nextstep.edu.calculator.data.dao.ResultDao
import camp.nextstep.edu.calculator.data.entity.ResultEntity

@Database(entities = [ResultEntity::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class CalculatorDatabase : RoomDatabase() {
    abstract fun resultDao(): ResultDao

    companion object {
        private const val DATABASE_NAME = "calculator_database"

        @Volatile
        private var instance: CalculatorDatabase? = null

        fun getInstance(context: Context): CalculatorDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): CalculatorDatabase {
            return Room.databaseBuilder(context, CalculatorDatabase::class.java, DATABASE_NAME).build()
        }
    }
}
