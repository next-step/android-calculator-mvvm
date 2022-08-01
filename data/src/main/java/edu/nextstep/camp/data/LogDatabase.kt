package edu.nextstep.camp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Log::class], version = 1)
abstract class LogDatabase: RoomDatabase() {
    abstract fun logDao(): LogDao

    companion object {
        private var instance: LogDatabase? = null

        @Synchronized
        fun getInstance(context: Context): LogDatabase? {
            if (instance == null) {
                synchronized(LogDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LogDatabase::class.java,
                        "log-database"
                    ).build()
                }
            }
            return instance
        }
    }
}