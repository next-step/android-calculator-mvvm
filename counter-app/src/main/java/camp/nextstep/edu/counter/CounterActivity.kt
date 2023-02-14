package camp.nextstep.edu.counter

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import camp.nextstep.edu.counter.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCounterBinding
    private val viewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initObservers()
    }

    private fun initObservers() {
        viewModel.checkLessThanZero.observe(this) {
            if (it) { showToastMessage() }
        }
    }

    private fun showToastMessage() {
        Toast.makeText(this, "0 이하로 내릴 수 없습니다", Toast.LENGTH_LONG).show()
    }
}
