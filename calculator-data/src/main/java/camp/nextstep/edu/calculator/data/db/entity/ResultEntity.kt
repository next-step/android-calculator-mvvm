package camp.nextstep.edu.calculator.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "result_records")
data class ResultEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val expression: String,
    val answer: String
)
