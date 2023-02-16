package camp.nextstep.edu.counter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.counter.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCounterBinding
    private val viewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        observeError()
    }

    private fun init() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun observeError() {
        viewModel.tryCountDownWhenZero.observe(this) {
            if (it) {
                Toast.makeText(this, "0 이하로 내릴 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
