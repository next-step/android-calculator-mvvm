package camp.nextstep.edu.calculator.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.model.Record

@Entity(tableName = "records")
internal data class RecordEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val expression: String,
    val result: Int
) {
    fun toDomain(): Record = Record(
        expression = Expression(expression.split(" ")),
        result = result,
    )

    companion object {
        fun from(record: Record): RecordEntity = RecordEntity(
            expression = record.expression.toString(),
            result = record.result,
        )
    }
}