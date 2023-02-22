package camp.nextstep.edu.calculator.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import camp.nextstep.edu.calculator.data.db.dao.ResultRecordsDao
import camp.nextstep.edu.calculator.data.db.entity.ResultEntity


@Database(entities = [ResultEntity::class], version = 1)
@TypeConverters(ListConverter::class)
abstract class CalculatorDatabase : RoomDatabase() {

    abstract fun resultRecordsDao(): ResultRecordsDao
}
