package edu.nextstep.camp.domain

import edu.nextstep.camp.domain.VO

interface EntityMapper {
    fun toVO(list: List<LogEntity>): List<VO>
}