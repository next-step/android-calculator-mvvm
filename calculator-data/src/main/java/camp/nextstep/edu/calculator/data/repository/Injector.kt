package camp.nextstep.edu.calculator.data.repository

import android.content.Context
import camp.nextstep.edu.calculator.data.database.RecordDatabase
import camp.nextstep.edu.calculator.domain.repository.RecordRepository

object Injector {
    fun provideRecordRepository(context: Context): RecordRepository {
        return RecordRepositoryImpl(RecordDatabase.getDatabase(context).recordDao())
    }
}
