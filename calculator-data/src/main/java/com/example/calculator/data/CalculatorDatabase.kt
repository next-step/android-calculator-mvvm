package com.example.calculator.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RenameTable
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities =
    [MemoryEntity::class],
    version = 1
)
abstract class CalculatorDatabase : RoomDatabase() {
    abstract fun CalculatorDao(): CalculatorDao

    companion object {
        private var instance: CalculatorDatabase? = null

        @Synchronized
        fun getInstance(context: Context): CalculatorDatabase? {
            if (instance == null)
                synchronized(CalculatorDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CalculatorDatabase::class.java,
                        "calculator.db"
                    ).build()
                }
            return instance
        }
    }
}
