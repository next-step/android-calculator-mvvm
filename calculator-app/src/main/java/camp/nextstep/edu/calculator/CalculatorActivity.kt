package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.data.injector.Injector
import camp.nextstep.edu.calculator.databinding.ActivityCalculatorBinding
import camp.nextstep.edu.calculator.domain.RecordRepository

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding

    private val viewModel: CalculatorViewModel by viewModels {
        val repository: RecordRepository = Injector.provideRecordRepository(this)
        CalculatorViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalculatorBinding.inflate(layoutInflater)

        setContentView(binding.root)
        with(binding) {
            vm = viewModel
            lifecycleOwner = this@CalculatorActivity
        }
        observerViewModel()
    }

    private fun observerViewModel() {
        viewModel.showToastMessage.observe(this) {
            showIncompleteExpressionError()
        }
    }

    private fun showIncompleteExpressionError() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }
}
