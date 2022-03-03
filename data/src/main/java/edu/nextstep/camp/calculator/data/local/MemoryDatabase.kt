package edu.nextstep.camp.calculator.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MemoryEntity::class], version = 1)
internal abstract class MemoryDatabase : RoomDatabase() {
    abstract fun getMemoryDao(): MemoryDao
}
