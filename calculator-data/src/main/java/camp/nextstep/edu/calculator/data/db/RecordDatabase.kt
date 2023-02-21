package camp.nextstep.edu.calculator.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import camp.nextstep.edu.calculator.data.model.RecordEntity


@Database(
    entities = [RecordEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RecordDatabase : RoomDatabase() {

    abstract fun recordDao(): RecordDao

    companion object {
        @Volatile
        private var INSTANCE: RecordDatabase? = null

        private fun buildDatabase(context: Context): RecordDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                RecordDatabase::class.java,
                "records"
            ).build()

        fun getInstance(context: Context): RecordDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
    }
}