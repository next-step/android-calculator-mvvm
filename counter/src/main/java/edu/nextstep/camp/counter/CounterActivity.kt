package edu.nextstep.camp.counter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import edu.nextstep.camp.counter.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {
    private val viewModel: CounterViewModel by viewModels()
    private lateinit var binding: ActivityCounterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_counter)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        observeMinusCount()
    }

    private fun observeMinusCount() {
        viewModel.isMinusCount.observe(this) {
            if (it == true) Toast.makeText(this, "0 이하로 내릴 수 없습니다", Toast.LENGTH_SHORT).show()
        }
    }
}
