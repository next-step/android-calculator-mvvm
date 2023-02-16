package camp.nextstep.edu.counter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import camp.nextstep.edu.counter.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCounterBinding
    private val viewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_counter)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setObserver()
    }

    private fun setObserver() {
        viewModel.onError.observe(this) {
            Toast.makeText(this@CounterActivity, "0 이하로 내릴 수 없습니다", Toast.LENGTH_SHORT).show()
        }
    }
}
