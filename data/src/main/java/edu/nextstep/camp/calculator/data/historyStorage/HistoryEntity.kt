package edu.nextstep.camp.calculator.data.historyStorage

import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.nextstep.camp.calculator.domain.history.History

@Entity(tableName = "history")
internal data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val expression: String,
    val result: Int
) {
    fun toDomainModel(): History = History(expression, result)
}
