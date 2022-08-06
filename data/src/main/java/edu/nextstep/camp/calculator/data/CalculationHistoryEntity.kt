package edu.nextstep.camp.calculator.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.nextstep.camp.calculator.domain.Expression

@Entity(
    tableName = "histories"
)
data class CalculationHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val expression: Expression,
    val result: String
)