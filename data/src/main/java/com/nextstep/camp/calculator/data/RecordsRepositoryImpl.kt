package com.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.domain.Record

internal class RecordsRepositoryImpl(
    private val recordDao: RecordDao,
    private val recordMapper: RecordMapper,
) : RecordsRepository {

    override suspend fun getAll(): List<Record> = recordDao
        .getAll()
        .map(recordMapper::toDomain)

    override suspend fun insert(record: Record) = recordDao
        .insert(recordMapper.toEntity(record))
}
