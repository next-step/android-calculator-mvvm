package edu.nextstep.camp.calculator.data.mapper

internal interface DataToDomainMapper<T> {
    fun map(): T
}

internal fun <T> List<DataToDomainMapper<T>>.map(): List<T> = map { it.map() }