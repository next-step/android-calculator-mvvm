package camp.nextstep.edu.counter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.counter.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCounterBinding
    private val counterViewModel: CounterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater).apply {
            setContentView(root)
            lifecycleOwner = this@CounterActivity
            viewModel = counterViewModel
        }
        addObserve()
    }

    private fun addObserve() {
        counterViewModel.isCounterLessThanZero.observe(this) {
            Toast.makeText(this, R.string.cannot_be_less_than_zero, Toast.LENGTH_SHORT).show()
        }
    }
}
