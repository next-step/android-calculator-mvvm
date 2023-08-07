package camp.nextstep.edu.calculator.data.module

import android.content.Context
import androidx.room.Room
import camp.nextstep.edu.calculator.data.database.HistoryDatabase

object DatabaseModule {

    fun provideDatabase(
        context: Context
    ): HistoryDatabase =
        Room.databaseBuilder(
            context,
            HistoryDatabase::class.java,
            "history"
        ).build()
}
