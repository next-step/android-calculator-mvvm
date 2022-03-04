package edu.nextstep.camp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CalculatorRecordEntity(
    @PrimaryKey(autoGenerate = true)
    val uuid: Int = 0,
    val expression: String,
    val result: String
)
