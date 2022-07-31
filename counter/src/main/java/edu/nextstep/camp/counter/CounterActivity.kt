package edu.nextstep.camp.counter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.counter.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCounterBinding
    private val counterViewModel: CounterViewModel by viewModels { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = counterViewModel

        observeViewState()
    }

    private fun observeViewState() {
        counterViewModel.onViewState.observe(this) {
            it.consume()?.run {
                when (this) {
                    is CounterState.Counted -> updateCountedNumber("$counteredNumber")
                    is CounterState.EMPTY -> updateCountedNumber("")
                    is CounterState.ZeroDownError -> {
                        updateCountedNumber("0")
                        showZeroDownError()
                    }
                }
            }
        }
    }

    private fun updateCountedNumber(displayText: String) {
        binding.textView.text = displayText
    }

    private fun showZeroDownError() {
        Toast.makeText(this, "0 이하로 내릴 수 없습니다.", Toast.LENGTH_SHORT).show()
    }
}
