package camp.nextstep.edu.counter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.counter.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {
	private lateinit var binding: ActivityCounterBinding
	private val counterViewModel: CounterViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityCounterBinding.inflate(layoutInflater)
		setContentView(binding.root)

		binding.lifecycleOwner = this
		binding.viewModel = counterViewModel

		binding.buttonUp.setOnClickListener {
			counterViewModel.countUp()
		}
		binding.buttonDown.setOnClickListener {
			val isSuccess = counterViewModel.countDown()
			showCountDownFailMessageIfNeed(isSuccess)
		}
	}

	private fun showCountDownFailMessageIfNeed(isSuccess: Boolean) {
		if (isSuccess.not()) {
			Toast.makeText(this, "0 이하로 내릴 수 없습니다", Toast.LENGTH_LONG).show()
		}
	}
}
