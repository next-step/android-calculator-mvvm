package com.github.dodobest.data.util

import com.github.dodobest.data.ResultRecordEntity
import com.github.dodobest.domain.ResultRecord

fun ResultRecord.toEntity() : ResultRecordEntity = ResultRecordEntity(this.expression, this.result)