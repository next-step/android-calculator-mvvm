package edu.nextstep.camp.counter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.counter.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCounterBinding
    private val viewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        observeEvent()
    }

    private fun observeEvent() {
        viewModel.liveEvent.observe(this) {
            val event = it.consume()
            if (event != null) {
                when (event) {
                    CounterEvent.ShowNegativeError -> {
                        Toast.makeText(this, getString(R.string.toast_no_negative), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
