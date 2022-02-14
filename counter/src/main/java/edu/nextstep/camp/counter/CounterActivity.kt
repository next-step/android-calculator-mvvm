package edu.nextstep.camp.counter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import edu.nextstep.camp.counter.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {

    private val counterViewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCounterBinding.inflate(layoutInflater).apply {
            viewModel = counterViewModel
            lifecycleOwner = this@CounterActivity
        }

        counterViewModel.eventShowErrorMessage.observe(this) {
            if (it.consumed) return@observe
            Toast.makeText(this, "0 이하로는 내릴 수 없습니다.", Toast.LENGTH_SHORT).show()
            it.consume()
        }

        setContentView(binding.root)
    }
}
