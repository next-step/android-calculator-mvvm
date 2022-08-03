package edu.nextstep.camp.calculator

import android.app.Application
import edu.nextstep.camp.calculator.data.DataInjector
import edu.nextstep.camp.calculator.data.RepositorySetting

/**
 * 클래스에 대한 간단한 설명이나 참고 url을 남겨주세요.
 * Created by jeongjinhong on 2022. 07. 31..
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        (DataInjector.provideCalculatorRepository() as? RepositorySetting)?.init(this)
    }

}
