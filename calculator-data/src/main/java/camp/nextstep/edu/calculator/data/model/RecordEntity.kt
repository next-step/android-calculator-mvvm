package camp.nextstep.edu.calculator.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import camp.nextstep.edu.calculator.domain.model.Record
import java.util.*

@Entity(tableName = "records")
data class RecordEntity(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val expression: String,
    val result: Int
) {
    fun toDomain(): Record = Record(
        expression = expression,
        result = result,
    )

    companion object {
        fun from(record: Record): RecordEntity = RecordEntity(
            expression = record.expression,
            result = record.result,
        )
    }
}