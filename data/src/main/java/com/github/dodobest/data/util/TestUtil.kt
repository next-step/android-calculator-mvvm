package com.github.dodobest.data.util

import com.github.dodobest.data.ResultRecordEntity

object TestUtil {
    fun createEmptyResultRecord(): ResultRecordEntity = ResultRecordEntity(
        expression = "",
        result = ""
    )
}