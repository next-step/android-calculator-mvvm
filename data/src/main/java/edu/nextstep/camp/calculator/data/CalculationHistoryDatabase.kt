package edu.nextstep.camp.calculator.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CalculationHistoryEntity::class], version = 1)
abstract class CalculationHistoryDatabase : RoomDatabase() {
    abstract fun calculationHistoryDao(): CalculationHistoryDao

    companion object {
        private const val NAME = "APP_DATABASE"

        fun create(context: Context): CalculationHistoryDatabase {
            return Room.databaseBuilder(
                context,
                CalculationHistoryDatabase::class.java,
                NAME
            ).fallbackToDestructiveMigration().build()
        }
    }
}