package edu.nextstep.camp.calculator.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class History(
    val expression: String,
    val resultValue: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}