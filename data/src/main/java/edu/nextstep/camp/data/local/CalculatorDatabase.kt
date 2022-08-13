package edu.nextstep.camp.data.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import edu.nextstep.camp.data.local.entity.CalculationHistoryEntity

@Database(
    entities = [CalculationHistoryEntity::class],
    version = 1
)
abstract class CalculatorDatabase: RoomDatabase() {
    abstract val calculationHistoryEntityDao: CalculationHistoryEntityDao

    companion object {
        @Volatile
        private lateinit var INSTANCE: CalculatorDatabase

        fun getCalculatorDatabase(application: Application): CalculatorDatabase {
            return synchronized(this) {
                if (this::INSTANCE.isInitialized.not()) {
                    INSTANCE = Room.databaseBuilder(
                        application,
                        CalculatorDatabase::class.java,
                        "calculator_db"
                    ).build()
                }

                INSTANCE
            }
        }
    }
}