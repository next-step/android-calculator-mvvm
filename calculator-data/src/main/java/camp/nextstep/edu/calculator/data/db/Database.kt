package camp.nextstep.edu.calculator.data.db

import android.content.Context
import androidx.room.Room


object Database {

    private val db: CalculatorDatabase? = null


    fun getDatabase(applicationContext: Context) =
        db ?: Room.databaseBuilder(
            context = applicationContext,
            klass = CalculatorDatabase::class.java,
            name = "CalculatorDatabase"
        ).build()
}
