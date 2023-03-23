package camp.nextstep.edu.calculator.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import camp.nextstep.edu.calculator.domain.model.Expression
import camp.nextstep.edu.calculator.domain.model.Record

@Entity(tableName = "record_table")
data class RecordEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val expression: String,
    val result: Int
) {

    fun toRecord(): Record = Record(
        expression = Expression(expression.split(" ")),
        result = result
    )

    companion object {
        fun create(record: Record): RecordEntity = RecordEntity(
            expression = record.expression.toString(),
            result = record.result
        )
    }
}
