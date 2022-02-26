package com.github.dodobest.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ResultRecord::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun resultRecordDao(): ResultRecordDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "resultRecord-db"
                ).allowMainThreadQueries().build()
            }
        }
    }
}