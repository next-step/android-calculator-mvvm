package camp.nextstep.edu.calculator.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RecordEntity::class], version = 1)
abstract class RecordDatabase: RoomDatabase() {
	internal abstract fun recordDao(): RecordDao
}