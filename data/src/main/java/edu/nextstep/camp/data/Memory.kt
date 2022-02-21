package edu.nextstep.camp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Memory(
    @ColumnInfo
    val expression: String,

    @ColumnInfo
    val result: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)