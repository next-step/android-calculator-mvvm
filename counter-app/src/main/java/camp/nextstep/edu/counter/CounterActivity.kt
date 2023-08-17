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
        initViewModelObserve()
        setContentView(binding.root)
    }

    private fun initBinding() {
        binding = ActivityCounterBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun initViewModelObserve() {
        viewModel.toastEvent.observe(this) { msg ->
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }
}
