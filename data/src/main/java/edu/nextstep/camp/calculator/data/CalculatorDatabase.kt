package edu.nextstep.camp.calculator.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CalculationMemory::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun calculatorDao(): CalculatorDao

    companion object {
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return instance ?: Room.databaseBuilder(
                context,
                AppDataBase::class.java, "calculator_database"
            ).build()
        }
    }
}