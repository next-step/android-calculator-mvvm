package camp.nextstep.edu.counter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import camp.nextstep.edu.counter.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {
    private val counterViewModel: CounterViewModel by viewModels()
    lateinit var binding: ActivityCounterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_counter)
        with(binding) {
            viewModel = counterViewModel
            lifecycleOwner = this@CounterActivity
        }
        setupListener()
    }

    private fun setupListener() {
        binding.buttonDown.setOnClickListener {
            val result = counterViewModel.downCount()
            if (!result.isSuccess) {
                Toast.makeText(this, "0 이하로 내릴 수 없습니다", Toast.LENGTH_SHORT).show()
            }
        }
        binding.buttonUp.setOnClickListener {
            counterViewModel.upCount()
        }
    }

}
