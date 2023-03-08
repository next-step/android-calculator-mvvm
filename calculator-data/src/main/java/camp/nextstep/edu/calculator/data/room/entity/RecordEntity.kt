package camp.nextstep.edu.calculator.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import camp.nextstep.edu.calculator.domain.model.Record

@Entity(tableName = "RecordEntity")
data class RecordEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "statement") val statement: String,
    @ColumnInfo(name = "result") val result: Int
) {
    fun toDomain(): Record {
        return Record(
            id = id,
            statement = statement,
            result = result
        )
    }

    companion object {
        val EMPTY = RecordEntity(
            id = 0,
            statement = "",
            result = 0
        )

        fun from(record: Record): RecordEntity =
            RecordEntity(
                id = record.id,
                statement = record.statement,
                result = record.result
            )
    }
}