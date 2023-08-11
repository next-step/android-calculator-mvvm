package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.CalculatorViewModel.EventType
import camp.nextstep.edu.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding

    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.event.observe(this) { event ->
            when (event) {
                EventType.SHOW_TOAST -> {
                    showIncompleteExpressionError()
                }
            }
        }
    }

    private fun showIncompleteExpressionError() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }
}
