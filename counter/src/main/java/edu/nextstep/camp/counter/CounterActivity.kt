package edu.nextstep.camp.counter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import edu.nextstep.camp.counter.databinding.ActivityCounterBinding
import kotlinx.coroutines.launch

class CounterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCounterBinding
    private val counterVM: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@CounterActivity
            counterVM = this@CounterActivity.counterVM
        }
        setContentView(binding.root)

        observeErrorEvent()
    }

    private fun observeErrorEvent() = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            counterVM.errorEvent
                .collect {
                    when (it.cause) {
                        is DecrementMinimumValueException -> showToast(resources.getString(R.string.less_than_minimum))
                    }
                }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
