package camp.nextstep.edu.counter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.counter.databinding.ActivityCounterBinding
import camp.nextstep.edu.counter.viewmodel.MainViewModel
import camp.nextstep.edu.counter.viewmodel.ViewModelFactory

class CounterActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels { ViewModelFactory() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.model = viewModel
        viewModel.uiState.observe(this) {
            Toast.makeText(this@CounterActivity, R.string.error_under_zero, Toast.LENGTH_SHORT).show()
        }
    }
}
