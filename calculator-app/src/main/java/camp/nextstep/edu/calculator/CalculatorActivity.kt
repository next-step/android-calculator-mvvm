package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityCalculatorBinding
import camp.nextstep.edu.calculator.domain.Expression
import camp.nextstep.edu.calculator.domain.Operator

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding

    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBindingVariables()
        initObserver()
    }

    private fun setBindingVariables() {
        with(binding) {
            vm = viewModel
        }
    }

    private fun initObserver() {
        with(viewModel) {
            expression.observe(this@CalculatorActivity) {
                binding.textView.text = it.toString()
            }
            inCompleteExpressionEvent.observe(this@CalculatorActivity) {
                Toast.makeText(this@CalculatorActivity, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
