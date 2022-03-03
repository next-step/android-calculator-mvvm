package com.github.dodobest.data

object TestUtil {
    fun createResultRecord(expression: String, result: String) = ResultRecord(
        expression = expression,
        result = result
    )
}