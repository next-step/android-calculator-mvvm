package com.nextstep.camp.calculator.data

interface Mapper<E, D> {

    fun toDomain(entity: E): D

    fun toEntity(domain: D): E
}
