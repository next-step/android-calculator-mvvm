package edu.nextstep.camp.calculator.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.nextstep.camp.calculator.domain.model.Memory

@Entity
internal data class History(
    val expression: String,
    val resultValue: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0

    fun convertHistoryToMemory() : Memory {
        return Memory(
            expression = this.expression,
            resultValue = this.resultValue,
        )
    }
}