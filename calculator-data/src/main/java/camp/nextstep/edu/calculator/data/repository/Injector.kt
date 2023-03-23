package camp.nextstep.edu.calculator.data.repository

import android.content.Context
import camp.nextstep.edu.calculator.data.database.RecordDao
import camp.nextstep.edu.calculator.data.database.RecordDatabase
import camp.nextstep.edu.calculator.domain.repository.RecordRepository

object Injector {
    fun provideRecordRepository(
        context: Context,
        recordDatabase: RecordDatabase = RecordDatabase.getDatabase(context)
    ): RecordRepository {
        return RecordRepositoryImpl(recordDatabase.recordDao())
    }
}
