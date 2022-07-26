package edu.nextstep.camp.counter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.counter.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {

    private val binding: ActivityCounterBinding by lazy {
        ActivityCounterBinding.inflate(layoutInflater)
    }
    private val viewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initObserver()
    }

    private fun initBinding() {
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun initObserver() {
        viewModel.countFailed.observe(this) {
            if (it == false) return@observe
            Toast
                .makeText(this, R.string.count_failed_message, Toast.LENGTH_LONG)
                .show()
        }
    }
}
