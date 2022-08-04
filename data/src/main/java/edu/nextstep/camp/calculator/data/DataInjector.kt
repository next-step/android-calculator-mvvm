package edu.nextstep.camp.calculator.data

import android.content.Context
import edu.nextstep.camp.calculator.domain.CalculatorRepository

/**
 * 클래스에 대한 간단한 설명이나 참고 url을 남겨주세요.
 * Created by jeongjinhong on 2022. 08. 03..
 */
object DataInjector {
    private fun provideCalculatorDatabase(context: Context) = CalculatorDatabase.create(context)

    fun provideCalculatorRepository(context: Context): CalculatorRepository {
        val database = provideCalculatorDatabase(context)
        return CalculatorRepositoryImpl(database.calculationRecordsDao())
    }
}