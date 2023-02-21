package camp.nextstep.edu.calculator.data.injector

import android.content.Context
import camp.nextstep.edu.calculator.data.db.RecordDatabase
import camp.nextstep.edu.calculator.data.repository.RecordRepositoryImpl
import camp.nextstep.edu.calculator.domain.RecordRepository

object Injector {

    private fun provideRecordDataBase(context: Context): RecordDatabase {
        return RecordDatabase.getInstance(context)
    }

    fun provideRecordRepository(context: Context): RecordRepository {
        return RecordRepositoryImpl(provideRecordDataBase(context).recordDao())
    }
}