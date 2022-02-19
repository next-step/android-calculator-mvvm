package edu.nextstep.camp.calculator

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Memory::class], version = 1)
internal abstract class MemoryDatabase : RoomDatabase() {

    abstract fun getMemoryDao(): MemoryDao

    companion object {

        private var INSTANCE: MemoryDatabase? = null

        fun getInstance(context: Context): MemoryDatabase {
            if (INSTANCE == null) {
                synchronized(MemoryDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        MemoryDatabase::class.java,
                        "memory.db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}