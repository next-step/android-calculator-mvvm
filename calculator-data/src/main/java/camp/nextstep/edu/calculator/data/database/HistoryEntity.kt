package camp.nextstep.edu.calculator.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true) var uid: Int = 0,
    var statement: String?,
    var result: Int
)