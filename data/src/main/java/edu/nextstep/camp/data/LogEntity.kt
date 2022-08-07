package edu.nextstep.camp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LogEntity(
    var expressionText: String,
    var result: String
): DataEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
