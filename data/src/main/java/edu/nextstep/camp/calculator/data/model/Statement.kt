package edu.nextstep.camp.calculator.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.nextstep.camp.calculator.data.mapper.DataToDomainMapper
import edu.nextstep.camp.calculator.domain.model.RecordStatement
import java.util.UUID

@Entity(tableName = "statement")
data class Statement(
    @PrimaryKey val uuid: UUID,
    val expression: String,
    val calculateResult: String
) : DataToDomainMapper<RecordStatement> {
    override fun map() = RecordStatement(
        uuid = uuid,
        expression = expression,
        calculateResult = calculateResult
    )
}
