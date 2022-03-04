package com.github.dodobest.data

import com.github.dodobest.domain.ResultRecord

object ResultRecordMapper {

    fun mapperToResultRecord(resultRecordEntity: ResultRecordEntity): ResultRecord {
        return ResultRecord(
            expression = resultRecordEntity.expression,
            result = resultRecordEntity.result
        )
    }

    fun mapperToResultRecordEntity(resultRecord: ResultRecord): ResultRecordEntity {
        return ResultRecordEntity(
            expression = resultRecord.expression,
            result = resultRecord.result
        )
    }
}