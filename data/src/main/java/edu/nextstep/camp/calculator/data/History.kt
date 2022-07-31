package edu.nextstep.camp.calculator.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by link.js on 2022. 07. 31..
 */

@Entity
data class History(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val expression: String,
    val result: Int,
)
