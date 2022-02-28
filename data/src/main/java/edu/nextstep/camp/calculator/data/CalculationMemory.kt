package edu.nextstep.camp.calculator.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CalculationMemory(
    val expression: String,

    val result: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)