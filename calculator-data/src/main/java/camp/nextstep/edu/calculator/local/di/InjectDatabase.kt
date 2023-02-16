package camp.nextstep.edu.calculator.local.di

import android.content.Context
import androidx.room.Room
import camp.nextstep.edu.calculator.local.db.CalculatorDatabase

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