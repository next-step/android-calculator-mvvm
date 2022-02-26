package com.github.dodobest.data

class DataHandler(
    private val database: AppDatabase
) {
    fun getAllMemoryRecord() : MutableList<ResultRecord> {
        return database.resultRecordDao().getAll()
    }

    fun insertRecord(statement: String, result: String) {
        database.resultRecordDao().insertResultRecord(
            ResultRecord(
                statement,
                "= $result"
            )
        )
    }
}