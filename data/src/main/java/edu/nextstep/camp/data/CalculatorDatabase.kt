package edu.nextstep.camp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CalculatorHistoryEntity::class], version = 1)
internal abstract class CalculatorDatabase : RoomDatabase() {
    abstract fun calculatorDao(): CalculatorDao

    companion object {
        private var instance: CalculatorDatabase? = null

        fun getInstance(context: Context): CalculatorDatabase {
            return instance ?: Room.databaseBuilder(
                context,
                CalculatorDatabase::class.java, "calculator_database"
            ).build()
        }
    }
}