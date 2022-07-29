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
        setContentView(R.layout.activity_counter)
        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()

        observeViewModel()

    }

    private fun setupViewModel() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun observeViewModel() {
        viewModel.errorEvent.observe(this) {
            Toast.makeText(this, R.string.cant_below_zero, Toast.LENGTH_SHORT).show()
        }
    }


}
