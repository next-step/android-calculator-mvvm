package camp.nextstep.edu.calculator.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Result::class], version = 1)
abstract class ResultDatabase : RoomDatabase() {
    abstract fun resultDao(): ResultDao

    companion object {
        private const val databaseName = "result"
        private var Instance: ResultDatabase? = null

        fun getInstance(context: Context): ResultDatabase? {
            if (Instance == null) {
                synchronized(ResultDatabase::class) {
                    Instance = Room.databaseBuilder(
                        context.applicationContext,
                        ResultDatabase::class.java,
                        databaseName
                    ).build()
                }
            }
            return Instance
        }
    }
}
