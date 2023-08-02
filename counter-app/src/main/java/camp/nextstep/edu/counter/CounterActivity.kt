package camp.nextstep.edu.counter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import camp.nextstep.edu.counter.databinding.ActivityCounterBinding
import camp.nextstep.edu.counter.viewmodel.CounterUiState
import camp.nextstep.edu.counter.viewmodel.MainViewModel
import camp.nextstep.edu.counter.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

class CounterActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels { ViewModelFactory() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.model = viewModel

        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                when(uiState) {
                    is CounterUiState.Error ->
                        Toast.makeText(this@CounterActivity, uiState.exception, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
