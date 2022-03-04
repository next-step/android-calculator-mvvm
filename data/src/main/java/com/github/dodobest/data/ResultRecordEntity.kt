package com.github.dodobest.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "result_record")
data class ResultRecordEntity(
    @ColumnInfo val expression: String,
    @ColumnInfo val result: String,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)
