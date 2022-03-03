package edu.nextstep.camp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CalculatorHisotry(
    val expression: String?,
    val result: String?,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)
