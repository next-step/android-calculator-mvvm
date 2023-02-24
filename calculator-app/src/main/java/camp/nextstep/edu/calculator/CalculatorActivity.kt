package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityCalculatorBinding
import camp.nextstep.edu.calculator.domain.Expression

class CalculatorActivity : AppCompatActivity(), CalculatorContract.View {
    private lateinit var binding: ActivityCalculatorBinding
    override lateinit var presenter: CalculatorContract.Presenter
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setContentView(binding.root)
        observeLiveData()
    }

    override fun showExpression(expression: Expression) {
        binding.textView.text = expression.toString()
    }

    override fun showResult(result: Int) {
        binding.textView.text = result.toString()
    }

    override fun showIncompleteExpressionError() {
        Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT).show()
    }

    private fun observeLiveData() {
        viewModel.expression.observe(this) {
            binding.textView.text = it.toString()
        }

        viewModel.result.observe(this) {
            binding.textView.text = it.toString()
        }

        viewModel.isCalculatePossible.observe(this) {
            showIncompleteExpressionError()
        }
    }
}
