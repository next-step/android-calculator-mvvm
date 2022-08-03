package edu.nextstep.camp.calculator.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.nextstep.camp.calculator.domain.History

/**
 * Created by link.js on 2022. 07. 31..
 */

@Entity
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val expression: String,
    val result: Int,
) {
    fun toHistory(): History {
        return History(expression = expression, result = result)
    }

    companion object {
        fun from(history: History): HistoryEntity {
            return HistoryEntity(expression = history.expression, result = history.result)
        }
    }
}
