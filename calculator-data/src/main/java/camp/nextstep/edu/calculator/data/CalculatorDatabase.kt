package camp.nextstep.edu.calculator.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ResultExpressionEntity::class], version = 1)
abstract class CalculatorDatabase : RoomDatabase() {
    abstract fun getDao(): CalculatorDao

    companion object {
        private const val databaseName = "calculator.db"
        private var instance: CalculatorDatabase? = null

        fun getInstance(context: Context): CalculatorDatabase? {
            if (instance == null) {
                synchronized(CalculatorDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CalculatorDatabase::class.java,
                        databaseName
                    ).build()
                }
            }
            return instance
        }
    }
}
