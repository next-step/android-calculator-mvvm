package edu.nextstep.camp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Memory::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun memoryDao(): MemoryDao

    companion object {
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return instance ?: Room.databaseBuilder(
                context,
                AppDataBase::class.java, "calculator.db"
            ).build()
        }
    }
}