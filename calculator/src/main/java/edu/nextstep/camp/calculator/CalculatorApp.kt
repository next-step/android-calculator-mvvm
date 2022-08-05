package edu.nextstep.camp.calculator

import android.app.Application

class CalculatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: CalculatorApp
    }
}