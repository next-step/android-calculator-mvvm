package edu.nextstep.camp.calculator.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "statement")
data class Statement(
    @PrimaryKey val uuid: UUID,
    val expression: String,
    val calculateResult: String
)
