package camp.nextstep.edu.calculator.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import camp.nextstep.edu.calculator.domain.data.Memory
import java.util.UUID

@Entity
data class MemoryEntity(
    @PrimaryKey val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "expression")
    var expression: String = "",

    @ColumnInfo(name = "result")
    var result: String = ""
) {
    companion object {
        fun from(memory: Memory): MemoryEntity {
            return MemoryEntity(expression = memory.expression, result = memory.result)
        }
    }
}
