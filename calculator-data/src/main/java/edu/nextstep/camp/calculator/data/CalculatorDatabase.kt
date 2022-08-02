package edu.nextstep.camp.calculator.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EvaluationRecordEntity::class], version = 1)
abstract class CalculatorDatabase : RoomDatabase() {
    abstract fun evaluationRecordDao(): EvaluationRecordDao
}
