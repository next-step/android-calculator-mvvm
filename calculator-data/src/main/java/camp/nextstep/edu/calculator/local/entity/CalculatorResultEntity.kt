package camp.nextstep.edu.calculator.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calculatorResult")
data class CalculatorResultEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 1,

    val expression: String?,
    val answer: Int?
)