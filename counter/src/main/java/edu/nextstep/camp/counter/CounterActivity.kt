package edu.nextstep.camp.counter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import edu.nextstep.camp.counter.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCounterBinding.inflate(layoutInflater) }
    private val counterViewModel by viewModels<CounterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.counterViewModel = counterViewModel
        binding.lifecycleOwner = this

        counterViewModel.failInfo.observe(this) {
            if (!it) return@observe
            Toast.makeText(applicationContext, "0 미만으로 내려갈 수 없습니다.", Toast.LENGTH_LONG).show()
        }
    }

}
