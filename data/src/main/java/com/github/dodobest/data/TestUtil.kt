package com.github.dodobest.data

object TestUtil {
    fun createResultRecord(expression: String, result: String) = ResultRecordEntity(
        expression = expression,
        result = result
    )
}