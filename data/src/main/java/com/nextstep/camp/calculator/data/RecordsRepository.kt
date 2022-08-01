package com.nextstep.camp.calculator.data

interface RecordsRepository {

    suspend fun getAll(): List<Record>

    suspend fun insert(record: Record)
}


