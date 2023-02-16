package camp.nextstep.edu.calculator

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CalculatorResultData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 1,

    val expression: String?,
    val answer: Int?
)