package edu.nextstep.camp.calculator.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import edu.nextstep.camp.calculator.data.model.Statement

@Database(entities = [Statement::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
internal abstract class CalculatorDatabase : RoomDatabase() {

    abstract fun calculatorDao(): CalculatorDao

    companion object {
        @Volatile
        private var INSTANCE: CalculatorDatabase? = null

        fun getDatabase(context: Context): CalculatorDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CalculatorDatabase::class.java,
                    "calculator_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
