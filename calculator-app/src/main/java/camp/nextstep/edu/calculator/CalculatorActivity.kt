package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import camp.nextstep.edu.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_calculator)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setObserver()
    }

    private fun setObserver() {
        viewModel.expression.observe(this) { expression ->
            binding.textView.text = expression.toString()
        }

        viewModel.result.observe(this) { result ->
            binding.textView.text = result
        }

        viewModel.showIncompleteExpressionError.observe(this) { isShow ->
            if (isShow) Toast.makeText(this, R.string.incomplete_expression, Toast.LENGTH_SHORT)
                .show()
        }
    }
}
