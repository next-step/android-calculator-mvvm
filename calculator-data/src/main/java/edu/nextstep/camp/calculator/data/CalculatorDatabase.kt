package edu.nextstep.camp.calculator.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EvaluationRecordEntity::class], version = 1)
abstract class CalculatorDatabase : RoomDatabase() {
    abstract fun evaluationRecordDao(): EvaluationRecordDao

    companion object {
        private const val CALCULATOR_DB_NAME = "database_calculator"

        @Volatile
        private var instance: CalculatorDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): CalculatorDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context = context).also {
                    instance = it
                }
            }

        private fun buildDatabase(context: Context): CalculatorDatabase {
            return Room.databaseBuilder(
                context,
                CalculatorDatabase::class.java,
                CALCULATOR_DB_NAME
            ).build()
        }
    }
}
