package camp.nextstep.edu.calculator

import android.content.Context
import androidx.room.Room

object InjectDatabase {

    private var db: CalculatorDatabase? = null

    fun getDB(context: Context): CalculatorDatabase? {
        if (db == null) {
            synchronized(CalculatorDatabase::class) {
                db = Room.databaseBuilder(
                    context = context,
                    klass = CalculatorDatabase::class.java,
                    name = "database-name"
                ).build()
            }
        }
        return db
    }
}