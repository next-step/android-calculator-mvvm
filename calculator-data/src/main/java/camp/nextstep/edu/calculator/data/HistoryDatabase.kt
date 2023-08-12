package camp.nextstep.edu.calculator.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HistoryEntity::class], version = 1, exportSchema = false)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao

    companion object {

        private const val HISTORY_DB = "HISTORY_DB"

        private lateinit var db: HistoryDatabase

        fun getInstance(context: Context): HistoryDatabase {
            if (!this::db.isInitialized) {
                db = build(context)
            }
            synchronized(db) {
                return db
            }
        }
        private fun build(context: Context): HistoryDatabase =
            Room.databaseBuilder(
                context,
                HistoryDatabase::class.java,
                HISTORY_DB
            ).build()
    }
}
