package camp.nextstep.edu.calculator

import android.app.Application
import android.content.Context

class CalculatorApp : Application() {
    lateinit var container: CalculatorAppContainer
    override fun onCreate() {
        super.onCreate()

        container = CalculatorAppContainer(applicationContext)
    }
}

val Context.container: CalculatorAppContainer
    get() = (applicationContext as CalculatorApp).container
