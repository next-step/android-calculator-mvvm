package edu.nextstep.camp.data

import edu.nextstep.camp.domain.LogVO


internal object LogEntityMapper: EntityMapper<LogEntity, LogVO> {
    override fun toVO(entity: LogEntity): LogVO {
        return LogVO(entity.id, entity.expressionText, entity.result)
    }

    override fun toEntity(vo: LogVO): LogEntity {
        return LogEntity(vo.expressionText, vo.result)
    }
}