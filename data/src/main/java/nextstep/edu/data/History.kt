package nextstep.edu.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class History(
    val rawExpression: String,
    val result: Int,
    @PrimaryKey(autoGenerate = true) val uid: Int = 0
)