package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding

    private val viewModel : CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            lifecycleOwner = this@CalculatorActivity
            viewModel = this@CalculatorActivity.viewModel
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.sideEffect.observe(this) {
            handleSideEffect(it)
        }
    }

    private fun handleSideEffect(event: Event<CalculatorViewModel.SideEffect>) {
        when (event.consume()) {
            CalculatorViewModel.SideEffect.UnknownError -> {
                showToast(R.string.unknown_error)
            }
            CalculatorViewModel.SideEffect.IncompleteExpressionError -> {
                showToast(R.string.incomplete_expression)
            }
            CalculatorViewModel.SideEffect.DivideByZeroError -> {
                showToast(R.string.divide_by_zero)
            }
            else -> {}
        }
    }

    private fun showToast(@StringRes stringRes: Int) {
        Toast.makeText(this, stringRes, Toast.LENGTH_SHORT).show()
    }
}
