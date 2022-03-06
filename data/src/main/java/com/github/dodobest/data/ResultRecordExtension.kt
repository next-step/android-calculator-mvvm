package com.github.dodobest.data

import com.github.dodobest.domain.ResultRecord

fun ResultRecord.toEntity() : ResultRecordEntity = ResultRecordEntity(this.expression, this.result)