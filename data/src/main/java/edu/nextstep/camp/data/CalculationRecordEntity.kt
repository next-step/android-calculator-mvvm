package edu.nextstep.camp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.nextstep.camp.domain.calculator.CalculationRecord
import edu.nextstep.camp.domain.calculator.Expression

@Entity(
    tableName = "calculation_records",
)
internal data class CalculationRecordEntity(
    @ColumnInfo(name = "expression") val expression: Expression,
    @ColumnInfo(name = "result") val result: Int,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) {
    companion object {
        @JvmStatic
        fun of(calculationRecord: CalculationRecord) = CalculationRecordEntity(
            expression = calculationRecord.expression,
            result = calculationRecord.result,
        )
    }
}

internal fun CalculationRecordEntity.mapToDomain() = CalculationRecord(expression, result)