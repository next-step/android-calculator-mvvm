package com.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.domain.Record

internal class RecordsRepositoryImpl(
    private val recordDao: RecordDao
) : RecordsRepository {

    override suspend fun getAll(): List<Record> = recordDao
        .getAll()
        .map { it.toDomain() }

    override suspend fun insert(record: Record) = recordDao
        .insert(record.toEntity())
}
