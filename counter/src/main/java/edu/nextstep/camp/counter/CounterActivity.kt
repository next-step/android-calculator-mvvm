package edu.nextstep.camp.counter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.counter.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {
    private val counterViewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCounterBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@CounterActivity
            viewModel = counterViewModel
        }.also { setContentView(it.root) }
        observeCounterViewModelEvent()
    }

    private fun observeCounterViewModelEvent() {
        counterViewModel.lessThenZeroEvent.observe(this) {
            Toast.makeText(this, "0 이하로 내릴 수 없습니다", Toast.LENGTH_SHORT)
                .show()
        }
    }
}
