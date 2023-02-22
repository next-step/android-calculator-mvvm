package camp.nextstep.edu.counter.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import camp.nextstep.edu.counter.R
import camp.nextstep.edu.counter.databinding.ActivityCounterBinding
import camp.nextstep.edu.counter.viewmodel.CounterViewModel

class CounterActivity : AppCompatActivity() {
    private val viewModel: CounterViewModel by viewModels()
    private lateinit var binding: ActivityCounterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_counter)
        binding.lifecycleOwner = this
        binding.counterViewModel = viewModel

        setOnErrorListener()
    }

    private fun setOnErrorListener() {
        viewModel.downError.observe(this) {
            Toast.makeText(this, "0 이하로 내릴 수 없습니다", Toast.LENGTH_LONG).show()
        }
    }
}
