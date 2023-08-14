package camp.nextstep.edu.calculator.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.RecordResource

@Entity
internal data class RecordEntity(
	@PrimaryKey(autoGenerate = true) val id: Int = 0,
	@ColumnInfo(name = "expression") val expression: String,
	@ColumnInfo(name = "result") val result: Int,
)

internal fun RecordEntity.asRecordResource() = RecordResource(
	id = id,
	expression = Expression.of(expression),
	result = result
)