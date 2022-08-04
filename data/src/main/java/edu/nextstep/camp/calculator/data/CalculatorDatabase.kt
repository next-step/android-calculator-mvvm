package edu.nextstep.camp.calculator.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import edu.nextstep.camp.calculator.data.dao.CalculateResultDao
import edu.nextstep.camp.calculator.data.model.CalculateResultEntity

@Database(entities = [CalculateResultEntity::class], version = 1)
abstract class CalculatorDatabase: RoomDatabase() {
    abstract fun calculateResultDao(): CalculateResultDao

    companion object {
        fun getDatabase(context: Context): CalculatorDatabase = Room.databaseBuilder(
            context,
            CalculatorDatabase::class.java,
            "calculator_database"
        ).build()
    }
}
