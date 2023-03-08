package camp.nextstep.edu.calculator.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import camp.nextstep.edu.calculator.data.room.CalculatorDatabase.Companion.ROOM_VERSION
import camp.nextstep.edu.calculator.data.room.dao.CalculatorDao
import camp.nextstep.edu.calculator.data.room.entity.RecordEntity

@Database(
    entities = [RecordEntity::class],
    version = ROOM_VERSION,

    )
abstract class CalculatorDatabase : RoomDatabase() {
    abstract fun calculatorDao(): CalculatorDao

    companion object {
        const val ROOM_VERSION = 1
    }
}