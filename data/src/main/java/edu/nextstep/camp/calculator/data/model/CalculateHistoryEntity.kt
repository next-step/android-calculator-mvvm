package edu.nextstep.camp.calculator.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calculate_history")
data class CalculateHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val expression: String,
    val result: Int,
)
