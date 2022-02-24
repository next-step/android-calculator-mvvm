package edu.nextstep.camp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.nextstep.camp.domain.calculator.Expression

@Entity
data class CalculatorRecord(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val expression: Expression,
    val result: Int
)
