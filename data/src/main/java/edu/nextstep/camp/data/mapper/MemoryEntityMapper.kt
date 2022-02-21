package edu.nextstep.camp.data.mapper

import edu.nextstep.camp.calculator.domain.model.Memory
import edu.nextstep.camp.data.entity.MemoryEntity

fun MemoryEntity.mapToDomain(): Memory = Memory(
    this.expression,
    this.result
)