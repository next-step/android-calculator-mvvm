package edu.nextstep.camp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import edu.nextstep.camp.data.model.CalculatorRecordEntity

@Database(entities = [CalculatorRecordEntity::class], version = 1)
abstract class CalculatorDatabase : RoomDatabase() {
    abstract fun calculatorRecordDao(): CalculatorRecordDao

    companion object {
        @Volatile
        private var instance: CalculatorDatabase? = null

        fun getInstance(context: Context): CalculatorDatabase = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                CalculatorDatabase::class.java,
                "calculator_database"
            ).build().also { instance = it }
        }
    }
}
