package edu.nextstep.camp.calculator

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Memory(
    val expression: String,
    val result: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
