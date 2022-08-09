package edu.nextstep.camp.calculator.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calculate_result")
internal data class CalculateResultEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val expression: String,
    val result: Int,
)
