package edu.nextstep.camp.counter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import edu.nextstep.camp.counter.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCounterBinding

    private val counterViewModel: CounterViewModel by viewModels { ViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initObserve()
    }

    private fun initViewModel() {
        binding.lifecycleOwner = this
        binding.viewModel = counterViewModel
    }

    private fun initObserve() {
        counterViewModel.errorToast.observe(this) {
            if (it) {
                Toast.makeText(this, R.string.error_counter_under_zero, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
