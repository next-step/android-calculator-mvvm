package camp.nextstep.edu.calculator.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RecordEntity")
data class RecordEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "statement") val statement: String,
    @ColumnInfo(name = "result") val result: Int
) {
    companion object {
        val EMPTY = RecordEntity(
            id = 0,
            statement = "",
            result = 0
        )
    }
}