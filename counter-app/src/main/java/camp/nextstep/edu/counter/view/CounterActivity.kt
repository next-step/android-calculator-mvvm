package camp.nextstep.edu.counter.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.counter.R
import camp.nextstep.edu.counter.viewmodel.CounterViewModel

class CounterActivity : AppCompatActivity() {
    private lateinit var viewModel: CounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)


    }
}
