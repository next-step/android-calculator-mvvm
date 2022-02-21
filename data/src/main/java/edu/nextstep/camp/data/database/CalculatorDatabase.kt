package edu.nextstep.camp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import edu.nextstep.camp.data.entity.MemoryEntity

@Database(
    entities = [
        MemoryEntity::class
    ],
    version = 1
)
internal abstract class CalculatorDatabase : RoomDatabase() {

    abstract fun calculatorDao(): CalculatorDao

    companion object {

        fun create(context: Context): CalculatorDatabase = Room.databaseBuilder(
            context.applicationContext,
            CalculatorDatabase::class.java,
            "calculator_database"
        ).build()
    }
}