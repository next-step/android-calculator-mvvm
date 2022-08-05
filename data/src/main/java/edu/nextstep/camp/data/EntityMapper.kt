package edu.nextstep.camp.data

interface EntityMapper<T, V> {
    fun toVO(entity: T): V

    fun toEntity(vo: V): T
}