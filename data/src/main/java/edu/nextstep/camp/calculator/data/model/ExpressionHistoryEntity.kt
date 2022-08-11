package edu.nextstep.camp.calculator.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.nextstep.camp.calculator.data.mapper.DataToDomainMapper
import edu.nextstep.camp.calculator.domain.model.ExpressionHistory

@Entity(tableName = "expression_history")
data class ExpressionHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val expression: String,
    val result: Int
) : DataToDomainMapper<ExpressionHistory> {
    override fun mapper() = ExpressionHistory(
        expression = expression,
        result = result
    )
}