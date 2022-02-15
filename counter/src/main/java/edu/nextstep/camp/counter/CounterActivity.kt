package edu.nextstep.camp.counter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import edu.nextstep.camp.counter.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCounterBinding
    private val viewModel: CounterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_counter)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.showErrorMessage.observe(this) {
            Toast.makeText(this,"0 이하로는 내릴 수 없습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}
