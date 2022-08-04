package edu.nextstep.camp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LogEntity::class], version = 1)
abstract class LogDatabase: RoomDatabase() {
    abstract fun logDao(): LogDao

    companion object {
        fun getInstance(context: Context): LogDatabase {
           return Room.databaseBuilder(
               context.applicationContext,
               LogDatabase::class.java,
               "log-database"
           ).build()
        }
    }
}