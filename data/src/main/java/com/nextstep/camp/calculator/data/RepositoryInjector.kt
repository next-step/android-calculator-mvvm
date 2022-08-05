package com.nextstep.camp.calculator.data

import android.content.Context

object RepositoryInjector {
    
    fun provideRecordsRepository(context: Context): RecordsRepository {
        return RecordsRepositoryImpl(
            recordDao = RecordDatabase.getInstance(context).recordDao(),
            recordMapper = RecordMapper(),
        )
    }
}
