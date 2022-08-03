package edu.nextstep.camp.counter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import edu.nextstep.camp.counter.databinding.ActivityCounterBinding
import kotlinx.coroutines.launch

class CounterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCounterBinding
    private val counterVM: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@CounterActivity
            counterVM = this@CounterActivity.counterVM
        }
        setContentView(binding.root)

        observeShowToastEvent()
    }

    private fun observeShowToastEvent() = lifecycleScope.launch {
        counterVM.showToastEvent.collect { message ->
            showToast(message)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
