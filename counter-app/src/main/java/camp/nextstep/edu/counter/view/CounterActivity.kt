package camp.nextstep.edu.counter.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.counter.databinding.ActivityCounterBinding
import camp.nextstep.edu.counter.viewmodel.CounterViewModel

class CounterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCounterBinding

    private val viewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this@CounterActivity

        setBindingVariables()
        initObserver()
    }

    private fun setBindingVariables() {
        with(binding) {
            vm = viewModel
        }
    }

    private fun initObserver() {
        with(viewModel) {
            negativeEvent.observe(this@CounterActivity) {
                Toast.makeText(this@CounterActivity, "0 이하로 내릴 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
