package edu.nextstep.camp.calculator.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DATABASE_NAME = "calculator.db"

@Database(version = 1, entities = [History::class])
internal abstract class CalculatorDatabase : RoomDatabase() {
    abstract fun getHistoryDao(): HistoryDao

    companion object {
        @Volatile
        private var instance: CalculatorDatabase? = null

        fun getInstance(context: Context): CalculatorDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): CalculatorDatabase {
            return Room.databaseBuilder(
                context,
                CalculatorDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}