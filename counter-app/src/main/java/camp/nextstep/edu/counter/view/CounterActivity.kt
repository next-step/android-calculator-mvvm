package camp.nextstep.edu.counter.view

import android.os.Bundle
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
        binding.counterView = viewModel
    }
}
