package edu.nextstep.camp.data.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import edu.nextstep.camp.domain.calculator.CalculationHistory

@Database(
    entities = [CalculationHistory::class],
    version = 1
)
abstract class CalculatorDatabase: RoomDatabase() {
    abstract val calculationHistoryDao: CalculationHistoryDao

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