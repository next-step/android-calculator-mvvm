package edu.nextstep.camp.calculator.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Memory
import edu.nextstep.camp.calculator.domain.Operator

@Entity(tableName = "memory")
internal class MemoryEntity(
    val rawExpression: String,
    val result: Int,
    @PrimaryKey(autoGenerate = true) val id: Int
) {
    fun toDomain(): Memory {
        return Memory(
            id = id,
            expression = Expression.from(rawExpression),
            result = result
        )
    }

    companion object {
        fun from(memory: Memory): MemoryEntity = MemoryEntity(
            id = memory.id,
            rawExpression = memory.expression.toString(),
            result = memory.result
        )
    }
}
