package edu.nextstep.camp.calculator.data.model

import kotlinx.serialization.Serializable

@Serializable
data class HistoryData(
    val expression: String,
    val result: Int,
)
