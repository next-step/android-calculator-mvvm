package edu.nextstep.camp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.nextstep.camp.domain.CalculatorHistory

@Entity
internal data class CalculatorHistoryEntity(
    val expression: String,
    val result: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)

