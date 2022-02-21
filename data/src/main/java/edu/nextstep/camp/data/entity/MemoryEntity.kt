package edu.nextstep.camp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.nextstep.camp.calculator.domain.model.Memory
import java.util.*

@Entity(tableName = "memory")
data class MemoryEntity(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val expression: String,
    val result: Int
) {

    companion object {

        fun of(memory: Memory): MemoryEntity = MemoryEntity(
            expression = memory.expression,
            result = memory.result
        )
    }
}