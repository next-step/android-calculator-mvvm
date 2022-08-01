package edu.nextstep.camp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Log(
    var expressionText: String,
    var result: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
