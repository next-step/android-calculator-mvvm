package camp.nextstep.edu.calculator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorBinding
    private lateinit var adapter: CalculatorAdapter
    private val viewModel: CalculatorViewModel by viewModels {
        CalculatorViewModelFactory(
            (application as CalculatorApplication).calculatorRepository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initObserver()
    }

    private fun initBinding() {
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        adapter = CalculatorAdapter(this)
        binding.recyclerView.adapter = adapter
        setContentView(binding.root)
    }

    private fun initObserver() {
        viewModel.uiState.observe(this) { uiState ->
            when(uiState) {
                is UiState.Result -> {
                    binding.textView.text = uiState.result
                    setHistoryMode(uiState.historyMode)
                }
                is UiState.History -> adapter.submitList(uiState.history)
            }
        }

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

    private fun setHistoryMode(isHistoryMode: Boolean) {
        if (isHistoryMode) {
            binding.textView.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        } else {
            binding.textView.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        }
    }
}
