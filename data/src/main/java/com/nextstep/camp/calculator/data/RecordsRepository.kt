package com.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.domain.Record

interface RecordsRepository {

    suspend fun getAll(): List<Record>

    suspend fun insert(record: Record)
}


