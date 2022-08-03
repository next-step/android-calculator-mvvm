package com.nextstep.camp.calculator.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RecordEntity::class], version = 1)
abstract class RecordDatabase : RoomDatabase() {
    abstract fun recordDao(): RecordDao

    companion object {
        private const val DATABASE_NAME = "record-database"
        private var instance: RecordDatabase? = null

        @Synchronized
        fun getInstance(context: Context): RecordDatabase {
            if (instance == null) {
                synchronized(RecordDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RecordDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                }
            }
            return instance!!
        }
    }
}
