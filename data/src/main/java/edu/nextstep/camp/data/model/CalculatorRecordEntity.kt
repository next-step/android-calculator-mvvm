package edu.nextstep.camp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.nextstep.camp.domain.calculator.model.CalculatorRecord

@Entity
data class CalculatorRecordEntity(
    @PrimaryKey(autoGenerate = true)
    val uuid: Int = 0,
    val expression: String,
    val result: String
)

fun List<CalculatorRecordEntity>.toListOfDomain() = this.map { it.toDomain() }

fun CalculatorRecordEntity.toDomain() = CalculatorRecord(this.expression, this.result)
