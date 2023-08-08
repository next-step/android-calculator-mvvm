package camp.nextstep.edu.calculator.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "HistoryEntity")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true) var uid: Int = 0,
    var expressions: String?,
    var result: Int
)