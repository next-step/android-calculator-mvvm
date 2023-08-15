package camp.nextstep.edu.counter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.counter.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCounterBinding
    private val counterViewModel: CounterViewModel by viewModels { CounterViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = counterViewModel

        counterViewModel.showToastEvent.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}
