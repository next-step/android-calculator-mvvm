package camp.nextstep.edu.calculator.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import camp.nextstep.edu.calculator.domain.data.ResultExpression

@Entity
data class MemoryEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "expression")
    var expression: String = "",

    @ColumnInfo(name = "result")
    var result: String = ""
) {
    companion object {
        fun from(resultExpression: ResultExpression): MemoryEntity {
            return MemoryEntity(expression = resultExpression.expression, result = resultExpression.result)
        }
    }
}
