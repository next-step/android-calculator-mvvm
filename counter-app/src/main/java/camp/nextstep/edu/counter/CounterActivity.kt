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

        initBinding()
        initObserver()
    }

    private fun initBinding() {
        binding = ActivityCounterBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setContentView(binding.root)
    }

    private fun initObserver() {
        viewModel.uiEffect.observe(this) { uiEffect ->
            when (uiEffect) {
                is UiEffect.Error -> Toast.makeText(this, uiEffect.message, Toast.LENGTH_SHORT)
                    .show()

                else -> {}
            }
        }
    }
}
