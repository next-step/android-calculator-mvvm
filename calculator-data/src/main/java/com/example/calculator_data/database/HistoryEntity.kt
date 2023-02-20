package com.example.calculator_data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.History

@Entity
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true) var uid: Int = 0,
    var statement: String?,
    var result: Int
) {

    fun toHistory(): History {
        return History(statement ?: "", result)
    }

    companion object {
        fun fromHistory(history: History): HistoryEntity {
            return HistoryEntity(statement = history.statement, result = history.result)
        }
    }
}
