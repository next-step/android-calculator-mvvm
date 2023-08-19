package camp.nextstep.edu.calculator.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import camp.nextstep.edu.calculator.domain.data.ResultExpression

@Entity
data class ResultExpressionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "expression")
    val expression: String = "",

    @ColumnInfo(name = "result")
    val result: String = ""
) {
    companion object {
        fun from(resultExpression: ResultExpression): ResultExpressionEntity {
            return ResultExpressionEntity(expression = resultExpression.expression, result = resultExpression.result)
        }
    }
}
