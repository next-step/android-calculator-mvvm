package camp.nextstep.edu.calculator.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Result(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "expression")
    var expression: String = "",

    @ColumnInfo(name = "result")
    var result: String = ""
)
