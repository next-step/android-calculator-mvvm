package camp.nextstep.edu.calculator.data

import android.content.Context
import androidx.room.Room
import camp.nextstep.edu.calculator.domain.RecordRepository

object DataModule {

	private fun provideRecordDatabase(context: Context): RecordDatabase {
		return Room.databaseBuilder(
			context.applicationContext,
			RecordDatabase::class.java,
			"record.db"
		).build()
	}

	fun provideRecordRepository(
		context: Context,
		recordDatabase: RecordDatabase = provideRecordDatabase(context)
	): RecordRepository {
		return DefaultRecordRepository(recordDatabase.recordDao())
	}
}