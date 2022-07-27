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

        binding.apply {
            lifecycleOwner = this@CounterActivity
            vm = viewModel
        }

        viewModel.sideEffect.observe(this) { event ->
            if (event.consume() == CounterViewModel.SideEffect.NegativeCounter) {
                Toast.makeText(this, getString(R.string.negative_number_toast), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
