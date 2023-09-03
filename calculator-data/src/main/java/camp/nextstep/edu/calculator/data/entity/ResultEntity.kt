package camp.nextstep.edu.calculator.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import camp.nextstep.edu.calculator.domain.Result
import java.util.Date

@Entity("result")
data class ResultEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val expression: String,
    val result: Int,
    @ColumnInfo("created_at")
    val createdAt: Date = Date(),
) {

    fun mapToDomain(): Result =
        Result(
            id = id,
            expression = expression,
            result = result,
            createdAt = createdAt
        )
}
