package edu.nextstep.camp.data

import edu.nextstep.camp.domain.VO

interface EntityMapper<T: DataEntity, V: VO> {
    fun toVO(entity: T): V

    fun toEntity(vo: V): T
}