package camp.nextstep.edu.counter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.counter.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCounterBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.event.observe(this) { event ->
            event.consume()?.let {
                when (it) {
                    MainViewModel.EventType.SHOW_TOAST -> {
                        Toast.makeText(
                            this@CounterActivity, "0 이하로 내릴 수 없습니다", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}
