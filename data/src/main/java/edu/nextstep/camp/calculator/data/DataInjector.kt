package edu.nextstep.camp.calculator.data

import edu.nextstep.camp.calculator.domain.CalculatorRepository

/**
 * 클래스에 대한 간단한 설명이나 참고 url을 남겨주세요.
 * Created by jeongjinhong on 2022. 08. 03..
 */
object DataInjector {
    fun provideCalculatorRepository(): CalculatorRepository {
        return CalculatorRepositoryImpl
    }
}