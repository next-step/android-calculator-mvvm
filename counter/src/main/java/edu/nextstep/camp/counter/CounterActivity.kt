package edu.nextstep.camp.counter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.counter.CounterViewModel.ViewEvent
import edu.nextstep.camp.counter.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCounterBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<CounterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.event.observe(this) { event ->
            event.consume()?.let { handleViewEvent(it) }
        }
    }

    private fun handleViewEvent(viewEvent: ViewEvent) {
        when (viewEvent) {
            is ViewEvent.FailDecrement ->
                Toast.makeText(this, viewEvent.message, Toast.LENGTH_SHORT).show()
        }
    }
}
