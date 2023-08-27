package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorBinding
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initObserver()
    }

    private fun initBinding() {
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setContentView(binding.root)
    }

    private fun initObserver() {
        viewModel.uiEffect.observe(this) { uiEffect ->
            when (uiEffect) {
                is UiEffect.ShowErrorMessage -> Toast.makeText(
                    this,
                    uiEffect.message ?: getString(R.string.default_error_message),
                    Toast.LENGTH_SHORT
                ).show()

                UiEffect.InCompleteExpressionError -> Toast.makeText(
                    this,
                    R.string.incomplete_expression,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
