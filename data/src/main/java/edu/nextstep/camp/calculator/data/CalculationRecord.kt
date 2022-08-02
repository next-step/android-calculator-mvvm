package edu.nextstep.camp.calculator.data

import androidx.room.Entity

/**
 * 클래스에 대한 간단한 설명이나 참고 url을 남겨주세요.
 * Created by jeongjinhong on 2022. 08. 01..
 */
@Entity
class CalculationRecord() {
    var expression: String? = null
    var result: Int? = null

    constructor(expression: String, result: Int) : this() {
        this.expression = expression
        this.result = result
    }
}