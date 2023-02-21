package camp.nextstep.edu.counter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.counter.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {
    private val counterViewModel: CounterViewModel by viewModels()
    lateinit var binding: ActivityCounterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater)
        with(binding) {
            viewModel = counterViewModel
            lifecycleOwner = this@CounterActivity
        }
        setContentView(binding.root)
        observerViewMode()
    }

    private fun observerViewMode() {
        counterViewModel.showToastMessage.observe(this) {
            showToastIfNumberBelow0()
        }
    }

    private fun showToastIfNumberBelow0() {
        Toast.makeText(this, "0 이하로 내릴 수 없습니다", Toast.LENGTH_SHORT).show()
    }

}
