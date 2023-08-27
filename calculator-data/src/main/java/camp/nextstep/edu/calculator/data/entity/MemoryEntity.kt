package camp.nextstep.edu.calculator.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import camp.nextstep.edu.calculator.domain.Memory
import java.util.UUID

@Entity(tableName = "memory")
data class MemoryEntity(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val expression: String,
    val result: Int
) {
    fun toDomain(): Memory = Memory(
        expression = expression,
        result = result
    )

    companion object {
        fun from(memory: Memory): MemoryEntity = MemoryEntity(
            expression = memory.expression,
            result = memory.result
        )
    }
}
