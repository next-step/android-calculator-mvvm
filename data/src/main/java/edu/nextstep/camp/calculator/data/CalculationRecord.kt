package edu.nextstep.camp.calculator.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 클래스에 대한 간단한 설명이나 참고 url을 남겨주세요.
 * Created by jeongjinhong on 2022. 08. 01..
 */
@Entity
class CalculationRecord(
    val expression: String,
    val result: Int,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)