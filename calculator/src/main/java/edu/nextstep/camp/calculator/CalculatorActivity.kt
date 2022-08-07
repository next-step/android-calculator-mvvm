package edu.nextstep.camp.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import edu.nextstep.camp.calculator.data.di.RepositoryModule
import edu.nextstep.camp.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding

    private val viewModel : CalculatorViewModel by viewModels {
        CalculatorViewModelFactory(RepositoryModule.provideEvaluationRecordStoreRepository(applicationContext))
    }
    private val adapter: EvaluationHistoryAdapter by lazy { EvaluationHistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            lifecycleOwner = this@CalculatorActivity
            viewModel = this@CalculatorActivity.viewModel
            recyclerView.adapter = this@CalculatorActivity.adapter
        }

        observeSideEffect()
        collectEvaluationHistory()
    }

    private fun observeSideEffect() {
        viewModel.sideEffect.observe(this) {
            handleSideEffect(it)
        }
    }

    private fun collectEvaluationHistory() {
        lifecycleScope.launchWhenStarted {
            viewModel.evaluationHistory.collect {
                adapter.submitList(it)
            }
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
