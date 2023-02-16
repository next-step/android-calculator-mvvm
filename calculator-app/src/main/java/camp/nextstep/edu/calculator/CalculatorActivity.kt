package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityCalculatorBinding
import camp.nextstep.edu.calculator.domain.Operator

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding

    private val viewModel: CalculatorViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            vm = viewModel
            lifecycleOwner = this@CalculatorActivity
        }


        binding.buttonPlus.setOnClickListener { viewModel.addToExpression(Operator.Plus) }
        binding.buttonMinus.setOnClickListener { viewModel.addToExpression(Operator.Minus) }
        binding.buttonMultiply.setOnClickListener { viewModel.addToExpression(Operator.Multiply) }
        binding.buttonDivide.setOnClickListener { viewModel.addToExpression(Operator.Divide) }
        binding.buttonDelete.setOnClickListener { viewModel.removeLast() }
        binding.buttonEquals.setOnClickListener { viewModel.calculate() }

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
