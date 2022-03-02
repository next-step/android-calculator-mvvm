package edu.nextstep.camp.calculator.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Memory

@Entity(tableName = "memory")
internal class MemoryEntity(
    val expression: String,
    val result: Int,
    @PrimaryKey(autoGenerate = true) val id: Int
) {
    fun toDomain(): Memory = Memory(id = id, expression = Expression.EMPTY, result = result)

    companion object {
        fun from(memory: Memory): MemoryEntity = MemoryEntity(
            id = memory.id,
            expression = memory.expression.toString(),
            result = memory.result
        )
    }
}
