package edu.nextstep.camp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CalculationHistoryEntity(
    @PrimaryKey
    val id: Long? = null,
    val expressionText: String,
    val result: Int
)
