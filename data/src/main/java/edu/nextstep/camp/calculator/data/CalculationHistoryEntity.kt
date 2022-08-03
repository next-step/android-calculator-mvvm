package edu.nextstep.camp.calculator.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CalculationHistoryEntity(
    val expression: String,
    val result: Int,
    @PrimaryKey(autoGenerate = true) val id: Long
)