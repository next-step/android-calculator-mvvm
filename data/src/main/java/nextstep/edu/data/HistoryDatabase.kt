package nextstep.edu.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [History::class], version = 1)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao

    companion object {

        private var HistoryInstance: HistoryDatabase? = null

        fun getInstance(context: Context): HistoryDatabase {
            if (HistoryInstance == null) {
                synchronized(HistoryDatabase::class) {
                    HistoryInstance = Room.databaseBuilder(
                        context,
                        HistoryDatabase::class.java,
                        "history"
                    ).build()
                }
            }
            return HistoryInstance!!
        }
    }

}