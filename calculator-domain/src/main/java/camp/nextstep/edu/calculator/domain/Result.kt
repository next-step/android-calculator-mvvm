package camp.nextstep.edu.calculator.domain

import java.util.Date

data class Result(
    val id: Long = 0,
    val expression: String,
    val result: Int,
    val createdAt: Date = Date(),
)
