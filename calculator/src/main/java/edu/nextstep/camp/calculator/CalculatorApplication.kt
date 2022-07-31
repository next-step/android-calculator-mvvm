package edu.nextstep.camp.calculator

import android.app.Application
import edu.nextstep.camp.calculator.data.Injector

class CalculatorApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.applicationContext = this
    }
}
