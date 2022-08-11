package edu.nextstep.camp.calculator.data

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.nextstep.camp.calculator.data.model.ExpressionHistoryEntity
import edu.nextstep.camp.calculator.domain.model.ExpressionHistory
import edu.nextstep.camp.calculator.data.service.ExpressionHistoryDAO

@Database(entities = [ExpressionHistoryEntity::class], version = 1)
abstract class CalculatorDataBase : RoomDatabase() {
    abstract fun expressionHistoryDao(): ExpressionHistoryDAO
}
