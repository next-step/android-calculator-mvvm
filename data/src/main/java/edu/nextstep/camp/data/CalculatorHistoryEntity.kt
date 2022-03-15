package edu.nextstep.camp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.nextstep.camp.domain.CalculatorHistory

@Entity
data class CalculatorHistoryEntity(
    val expression: String,
    val result: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)

fun CalculatorHistoryEntity.toDomain(): CalculatorHistory= CalculatorHistory(
    this.expression,
    this.result
)
