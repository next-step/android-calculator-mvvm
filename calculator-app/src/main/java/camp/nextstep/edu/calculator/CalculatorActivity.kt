package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import camp.nextstep.edu.calculator.databinding.ActivityCalculatorBinding
import kotlinx.coroutines.launch

class CalculatorActivity : AppCompatActivity() {
	private lateinit var binding: ActivityCalculatorBinding
	private val calculatorViewModel by viewModels<CalculatorViewModel> {
		ViewModelFactory(this)
	}
	private val recordAdapter by lazy { RecordAdapter() }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityCalculatorBinding.inflate(layoutInflater)
		setContentView(binding.root)

		binding.lifecycleOwner = this
		binding.viewModel = calculatorViewModel
		binding.recyclerView.adapter = recordAdapter

		calculatorViewModel.expressionInCompleted.observe(this) {
			Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
		}

		lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.RESUMED) {
				calculatorViewModel.records.collect {
					recordAdapter.submitList(it)
				}
			}
		}
	}
}
