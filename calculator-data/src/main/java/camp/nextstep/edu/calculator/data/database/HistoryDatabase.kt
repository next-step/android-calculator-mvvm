package camp.nextstep.edu.calculator.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import camp.nextstep.edu.calculator.data.database.HistoryDatabase.Companion.ROOM_VERSION

@Database(entities = [HistoryEntity::class], version = ROOM_VERSION)
internal abstract class HistoryDatabase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao

    companion object {
        @Volatile
        private var INSTANCE: HistoryDatabase? = null
        const val ROOM_VERSION = 2

        private fun buildDatabase(context: Context): HistoryDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                HistoryDatabase::class.java,
                "history"
            ).build()

        fun getInstance(context: Context): HistoryDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
    }
}