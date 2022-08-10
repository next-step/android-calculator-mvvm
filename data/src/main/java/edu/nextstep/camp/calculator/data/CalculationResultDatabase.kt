package edu.nextstep.camp.calculator.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CalculationResultEntity::class], version = 2)
internal abstract class CalculationResultDatabase : RoomDatabase() {
    abstract fun calculationResultDao(): CalculationResultDao

    companion object {
        private const val NAME = "APP_DATABASE"

        fun create(context: Context): CalculationResultDatabase {
            return Room.databaseBuilder(
                context,
                CalculationResultDatabase::class.java,
                NAME
            ).fallbackToDestructiveMigration().build()
        }
    }
}