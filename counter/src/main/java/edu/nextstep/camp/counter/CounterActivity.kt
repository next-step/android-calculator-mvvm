package edu.nextstep.camp.counter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import edu.nextstep.camp.counter.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {
    private val viewModel: CounterViewModel by viewModels()
    private lateinit var binding: ActivityCounterBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBindingSetting()
        observeViewModel()
    }

    private fun setBindingSetting() {
        binding.lifecycleOwner = this@CounterActivity
        binding.viewModel = viewModel
    }

    private fun observeViewModel() {
        viewModel.countEvent.observe(this) {
            it.consume()?.let(::showToastWithCounterEvent)
        }
    }

    private fun showToastWithCounterEvent(event: CounterEvent) =
        Toast.makeText(this, event.stringId, Toast.LENGTH_SHORT).show()
}
