package edu.nextstep.camp.counter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import edu.nextstep.camp.counter.databinding.ActivityCounterBinding
import edu.nextstep.camp.counter.event.Event

class CounterActivity : AppCompatActivity() {

    lateinit var binding: ActivityCounterBinding
    private val counterViewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_counter)

        binding.lifecycleOwner = this
        binding.viewModel = counterViewModel

        counterViewModel.showEvent.observe(this) {
            handleEvent(it)
        }
    }

    private fun handleEvent(event: Event) {
        when(event) {
            is Event.Error -> {
                Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
