package edu.nextstep.camp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CalculatorRecord::class], version = 1)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun calculatorRecordDAO(): CalculatorRecordDAO

    companion object {
        private var INSTANCE: LocalDataBase? = null

        fun getInstance(context: Context) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(context, LocalDataBase::class.java, "local.db")
                .build()
                .also { INSTANCE = it }
        }
    }
}
