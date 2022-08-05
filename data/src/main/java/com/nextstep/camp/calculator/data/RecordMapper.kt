package com.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.domain.Record

internal class RecordMapper : Mapper<RecordEntity, Record> {

    override fun toDomain(entity: RecordEntity): Record =
        Record(
            expression = entity.expression,
            result = entity.result
        )

    override fun toEntity(domain: Record): RecordEntity =
        RecordEntity(
            expression = domain.expression,
            result = domain.result
        )
}
