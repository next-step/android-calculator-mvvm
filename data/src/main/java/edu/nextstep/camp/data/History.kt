package edu.nextstep.camp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by link.js on 2022. 07. 31..
 */

@Entity
data class History(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "expression") val expression: String,
    @ColumnInfo(name = "result") val result: Int,
)
