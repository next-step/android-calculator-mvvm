package edu.nextstep.camp.calculator.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
internal data class History(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val historyId: Int,
    @ColumnInfo(name = "formula") val formula: String,
    @ColumnInfo(name = "calculate_result") val calculateResult: String
) {
    constructor(formula: String, calculateResult: String): this(0, formula, calculateResult)
}