package camp.nextstep.edu.calculator

import android.app.Application
import camp.nextstep.edu.calculator.data.db.Database


class CalculatorApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: CalculatorApplication
        val database by lazy { Database.getDatabase(instance) }
    }
}
