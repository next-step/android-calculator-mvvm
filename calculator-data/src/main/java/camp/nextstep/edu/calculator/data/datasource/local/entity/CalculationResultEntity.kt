package camp.nextstep.edu.calculator.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import camp.nextstep.edu.calculator.domain.CalculationResult

@Entity(
    tableName = "calculation_result",
    primaryKeys = ["expression", "result"]
)
data class CalculationResultEntity(
    @ColumnInfo("expression") val expression: String,
    @ColumnInfo("result") val result: String
) {
    companion object {
        fun mapToDomainModel(entity: CalculationResultEntity): CalculationResult {
            return CalculationResult(
                expression = entity.expression,
                result = entity.result
            )
        }

        fun mapFromDomainModel(calculationResult: CalculationResult): CalculationResultEntity {
            return CalculationResultEntity(
                expression = calculationResult.expression,
                result = calculationResult.result,
            )
        }
    }
}